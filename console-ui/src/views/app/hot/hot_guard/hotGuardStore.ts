import { defineStore } from "pinia";
import {Setting} from "@/views/app/hot/hot_guard/hotSettingTypes";
import {updateHotGuardSetting} from "@/api/hot/hotGuardApi";
import {useSnackbarStore} from "@/stores/snackbarStore";

const snackbarStore = useSnackbarStore();

export const useHotGuardStore = defineStore({
  id: "hotGuard",
  state:()=>({
    settings: ref<Setting[]>([]),
    guards:[]
  }),

  getters:{
    getSettings(){
      return this.settings
    },
    getGuards(){
      return this.guards
    }
  },

  actions:{
    updateSetting(index:number, setting:Setting){
      const tempSetting = Object.assign({},setting)
      updateHotGuardSetting(tempSetting).then(res=>{
          if(res?.data["success"]){
            this.getSettings[index] = setting
            snackbarStore.showSuccessMessage("更新设置成功");
          }else{
            snackbarStore.showSuccessMessage("更新设置失败");
          }
        }
      )
    },

    updateSettingNoIndex(setting:Setting){
      updateHotGuardSetting(setting).then(res=>{
          if(res?.data["success"]){
            snackbarStore.showSuccessMessage("更新设置成功");
          }else{
            snackbarStore.showSuccessMessage("更新设置失败");
          }
        }
      )
    }
  }
})
