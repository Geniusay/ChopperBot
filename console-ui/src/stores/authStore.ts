import { defineStore } from "pinia";

import router from "@/router";

interface Profile {
  id: string;
  name: string;
  avatar: string;
  created: boolean;
}

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    user: null,
    profile: null as Profile | null,
  }),

  persist: {
    enabled: true,
    strategies: [{ storage: localStorage, paths: ["isLoggedIn"] }],
  },

  getters: {},

  actions: {
    setLoggedIn(payload: boolean) {
      this.isLoggedIn = payload;
    },

    registerWithEmailAndPassword(email: string, password: string) {
      router.push("/");
    },

    loginWithEmailAndPassword(email: string, password: string) {
      router.push("/");
    },

    loginWithGoogle() {
      router.push("/");
    },

    logout() {
      router.push({ name: "auth-signin" });
    },
  },
});
