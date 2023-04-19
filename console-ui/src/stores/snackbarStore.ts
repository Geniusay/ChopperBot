import { defineStore } from "pinia";

type MessageType = "" | "info" | "success" | "error" | "warning";

export const useSnackbarStore = defineStore({
  id: "snackbarStore",
  state: () => ({
    isShow: false,
    message: "",
    type: "",
  }),

  persist: {
    enabled: true,
    strategies: [{ storage: localStorage, paths: [""] }],
  },

  getters: {},
  actions: {
    showMessage(message: string) {
      this.isShow = true;
      this.message = message;
      this.type = "";
    },

    showErrorMessage(message: string) {
      this.isShow = true;
      this.message = message;
      this.type = "error";
    },
    showSuccessMessage(message: string) {
      this.isShow = true;
      this.message = message;
      this.type = "success";
    },
    showInfoMessage(message: string) {
      this.isShow = true;
      this.message = message;
      this.type = "info";
    },
    showWarningMessage(message: string) {
      this.isShow = true;
      this.message = message;
      this.type = "warning";
    },
  },
});
