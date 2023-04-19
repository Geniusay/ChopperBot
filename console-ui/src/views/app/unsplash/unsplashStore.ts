import { defineStore } from "pinia";
import { Photo } from "./unsplashTypes";

export const useUnsplashStore = defineStore({
  id: "upsplash",
  state: () => ({
    favoriteList: ref<Photo[]>([]),
  }),

  persist: {
    enabled: true,
    strategies: [{ storage: localStorage, paths: ["favoriteList"] }],
  },

  getters: {},
  actions: {
    addToFavorite(payload: Photo) {
      this.favoriteList.push(payload);
    },

    // This function will remove a photo from the favorite list.
    removeFromFavorite(payload: Photo) {
      // Find the index of the photo in the favorite list.
      const index = this.favoriteList.findIndex(
        (item) => item.id == payload.id
      );
      // If the photo exists in the favorite list, remove it.
      if (index > -1) {
        this.favoriteList.splice(index, 1);
      }
    },
  },
});
