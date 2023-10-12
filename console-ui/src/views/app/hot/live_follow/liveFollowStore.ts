import { defineStore } from "pinia";
import {FocusLiver} from "@/views/app/hot/live_follow/focusLiverTypes"


export const useLiveFollowStore = defineStore({
  id: "hotRecommend",
  state:()=>({
    dialog: ref(false),
    addDialog: ref(false),
    setting:ref({}),
    liveFollowList: ref<FocusLiver[]>([]),
  }),

  getters:{

  },

  actions:{
    deleteLiveFollow(liver:FocusLiver){
      // this.liveFollowList.value = this.liveFollowList.value.filter(item=>item.id!==liver.id)
      this.liveFollowList = this.liveFollowList.filter(item=>item.liver!==liver.liver)
    },
    addLiveFollow(liver:FocusLiver){
      this.liveFollowList.push(liver)
    },
  }
})
