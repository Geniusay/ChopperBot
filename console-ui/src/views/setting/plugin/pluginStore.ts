import { defineStore } from "pinia";
import { Plugin } from "./pluginTypes"
import {startPlugin, closePlugin, enablePlugin, disabledPlugin, allPlugins} from "@/api/pluginApi";
import { useSnackbarStore } from "@/stores/snackbarStore";



export const usePluginStore = defineStore({
  id: "plugin",
  state:()=>({
    pluginList: ref([]),
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
      {
        id: "disabled",
        title: "Disabled",
        color: "red"
      }
    ]
  }),

  getters:{
    async initList(){
        await allPlugins().then(res=>{
          this.pluginList = res.data
        })
      return this.pluginList
    },
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
         const snackbarStore = useSnackbarStore();
         // @ts-ignore
         if(res.code===200){
           plugin.register = true
           snackbarStore.showSuccessMessage("开启"+plugin.pluginName+"成功");
         }else{
           // @ts-ignore
           plugin.register = false;
           // @ts-ignore
           snackbarStore.showErrorMessage(res.msg)
         }
       })
    },

    closePlugin(plugin: Plugin){
      closePlugin(plugin.pluginName).then(res => {
        const snackbarStore = useSnackbarStore();
        // @ts-ignore
        if(res.code===200){
          plugin.register = false
          snackbarStore.showSuccessMessage("关闭"+plugin.pluginName+"成功");
        }else{
          // @ts-ignore
          plugin.register = true;
          // @ts-ignore
          snackbarStore.showErrorMessage(res.msg)
        }
      })
    },

    enablePlugin(plugin: Plugin){
      enablePlugin(plugin.pluginName).then(res => {
        const snackbarStore = useSnackbarStore();
        // @ts-ignore
        if(res.code === 200 && res.data.success === true){
          plugin.start = true
          snackbarStore.showSuccessMessage("开启" + plugin.pluginName + "自启动成功");
        }else{
          plugin.start = false;
          // @ts-ignore
          snackbarStore.showErrorMessage(res.msg?res.msg:("开启" + plugin.pluginName + "自启动失败"))
        }
      })
    },

    disabledPlugin(plugin: Plugin){
      disabledPlugin(plugin.pluginName).then(res => {
        const snackbarStore = useSnackbarStore();
        // @ts-ignore
        if(res.code === 200 && res.data.success === true){
          plugin.start = false
          snackbarStore.showSuccessMessage("关闭" + plugin.pluginName + "自启动成功");
        }else{
          plugin.start = true;
          // @ts-ignore
          snackbarStore.showErrorMessage(res.msg?res.msg:("关闭" + plugin.pluginName + "自启动失败"))
        }
      })
    }
  }
})
