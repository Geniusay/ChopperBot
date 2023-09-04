import { defineStore } from "pinia";
import { Plugin } from "./pluginTypes"
import {startPlugin,closePlugin} from "@/api/pluginApi";
import { useSnackbarStore } from "@/stores/snackbarStore";

const snackbarStore = useSnackbarStore();

export const usePluginStore = defineStore({
  id: "plugin",
  state:()=>({
    pluginList: [],
    currentLabel: "running",
    labels:[
      {
        id: "running",
        title: "Running",
        color: "green",
      },
      {
        id: "close",
        title: "Close",
        color: "red",
      },
      {
        id: "autoStart",
        title: "AutoStart",
        color: "blue"
      },
    ]
  }),

  getters:{
    getAllList(){
      return this.pluginList
    },

    getStartList(){
      return this.pluginList.filter((plugin:Plugin) => plugin.register);
    },

    getCloseList(){
      return this.pluginList.filter((plugin:Plugin) => !plugin.register);
    },

    getAutoStartList(){
      return this.pluginList.filter((plugin:Plugin) => plugin.start)
    },

    getLabelPluginList(){
      if(this.currentLabel == "running"){
        return this.getStartList();
      }else if(this.currentLable == "close"){
        return this.getCloseList();
      }else if(this.currentLable == "autoStart"){
        return this.getAutoStartList()
      }
    }
  },

  actions:{

    startPlugin(plugin: Plugin){
       startPlugin(plugin.pluginName).then(res => {
         if(res.code===200){
           plugin.register = true
           snackbarStore.showSuccessMessage("开启"+plugin.pluginName+"成功");
         }else{
           // @ts-ignore
           plugin.register = false;
           snackbarStore.showErrorMessage(res.msg)
         }
       })
    },

    closePlugin(plugin: Plugin){
      closePlugin(plugin.pluginName).then(res => {
        if(res.code===200){
          plugin.register = false
          snackbarStore.showSuccessMessage("关闭"+plugin.pluginName+"成功");
        }else{
          // @ts-ignore
          plugin.register = true;
          snackbarStore.showErrorMessage(res.msg)
        }
      })
    }
  }
})
