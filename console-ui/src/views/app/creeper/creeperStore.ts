import { defineStore } from "pinia";

export const useCreeperStore = defineStore({
  id: "plugin",
  state:()=>({
    creeperList: [],
  }),

  getters:{
    getAllList(){
      return this.creeperList
    },
  },

  actions:{

  }
})
