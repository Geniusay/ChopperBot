import { defineStore } from "pinia";

export const useAppStore = defineStore({
  id: "app",
  state: () => ({
    globalLoading: false,
  }),

  persist: {
    enabled: true,
    strategies: [{ storage: localStorage, paths: [] }],
  },

  getters: {},
  actions: {},
});
