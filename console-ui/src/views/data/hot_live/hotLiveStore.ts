import { defineStore } from "pinia";
import {getHotLive,
  getHotModule,
  getModuleLive
} from "@/api/hot/hotDataApi";

import {Module} from "@/views/data/hot_live/ModuleTypes";
import {Live} from "@/views/data/hot_live/LiveTypes";
import {useSnackbarStore} from "@/stores/snackbarStore";
const snackbarStore = useSnackbarStore();

export const useHotLiveStore = defineStore({
  id: "hotLive",
  state:()=>({
      platformModuleMap:new Map(),
      platformLiveMap:new Map(),
  }),

  getters:{

  },

  actions:{
     async getHotModuleData(platform:string){
        await getHotModule(platform).then(res=>{
            if(res.code==="403"){
              snackbarStore.showErrorMessage(res.msg)
              return
            }
           const moduleList = res.data['list'].map((item)=>{return item as Module})
           this.platformModuleMap.set(platform,moduleList)
        });

     },
     async getHotLiveData(platform:string){
        await getHotLive(platform).then(res=>{
          if(res.code==="403"){
             snackbarStore.showErrorMessage(res.msg)
          }
           const liveList = res.data['list'].map((item)=>{return item as Live})
           this.platformLiveMap.set(platform,liveList)
        });
     },
     async getModuleLiveData(moduleId:string,platform:string){
        await getModuleLive(moduleId,platform).then(res=>{
            if(res.code==="403"){
              snackbarStore.showErrorMessage(res.msg)
              return
            }
           const liveList = res.data['list'].map((item)=>{return item as Live})
           const module = this.platformModuleMap.get(platform).find((module)=>module.tagId===moduleId) as Module;
           module.lives = liveList
        })
     }
  }
})
