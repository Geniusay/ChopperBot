import { defineStore } from "pinia";
import {FollowDog} from "@/views/app/hot/heat_recommend/FollowDogTypes";


export const useHotRecommendStore = defineStore({
  id: "hotRecommend",
  state:()=>({
    followDogs: ref<FollowDog[]>([]),
    platforms: ['douyu','huya','bilibili','douyin','tiktok','twitch'],

  }),

  getters:{
    getFollowDogs(){
      return this.followDogs
    }
  },

  actions:{
    deleteFollowDog(dog:FollowDog){
      this.followDogs = this.followDogs.filter(oldDog=>oldDog.dogId!==dog.dogId)
    },
    addFollowDog(dog:FollowDog){
      this.followDogs.push(dog)
    },
   updateFollowDog(dog:FollowDog){
      let old = this.followDogs.find(oldDog=>dog.dogId===oldDog.dogId)
      this.followDogs.splice(this.followDogs.indexOf(old), 1, dog);
    }
  }
})
