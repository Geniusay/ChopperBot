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

  }
})
