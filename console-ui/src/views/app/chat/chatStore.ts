import { defineStore } from "pinia";
import type { Message, User } from "./chatTypes";

export const useChatStore = defineStore({
  id: "chat",
  state: () => ({
    apiKey: "",
    chatHistory: [],
    apiKeyDialog: false,
  }),

  persist: {
    enabled: true,
    strategies: [{ storage: localStorage, paths: ["chatHistory", "apiKey"] }],
  },

  getters: {
    getChatHistory() {
      return this.chatHistory;
    },
    // If you have set up an API key, please use your own key. If not, please use the one I provided.
    getApiKey: (state) => state.apiKey || import.meta.env.VITE_OPENAI_API_KEY,
  },
  actions: {
    saveApiKey(key: string) {
      this.apiKey = key;
    },

    addToHistory(payload: Message) {
      this.chatHistory.push(payload);
    },
    clearHistory() {
      this.chatHistory = [];
    },
    // 移除最后一条临时信息
    removeLatestMessage() {
      this.chatHistory.pop();
    },
  },
});
