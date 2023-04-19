import { defineStore } from "pinia";
import { Email } from "./emailTypes";
import { inboxList, starredList } from "./emailData";

export const useEmailStore = defineStore({
  id: "email",
  state: () => ({
    inbox: inboxList,
    starred: starredList,
    currentLabel: "work",
    labels: [
      {
        id: "work",
        title: "Work",
        color: "primary",
      },
      {
        id: "relaxation",
        title: "Relaxation",
        color: "green",
      },
      {
        id: "shopping",
        title: "Shopping",
        color: "teal",
      },
    ],
  }),

  // persist: {
  //   enabled: true,
  //   strategies: [
  //     {
  //       storage: localStorage,
  //       paths: ["todoList"],
  //     },
  //   ],
  // },

  getters: {
    getInboxList() {
      return this.inbox;
    },

    getStarredList() {
      return this.starred;
    },
  },
  actions: {
    // Add new todo
    addNewEmail(todo: Email) {
      const newEmail = {
        id: "_" + Math.random().toString(36).substring(2, 11),
        ...todo,
      };
      this.inbox.push(newEmail);
    },
    // update todo
    updateEmail(todo: Email) {
      const index = this.inbox.findIndex((item: Email) => item.id === todo.id);
      this.inbox.splice(index, 1, todo);
    },

    // Delete todo By Id
    deleteEmailById(todoId: number) {
      const index = this.inbox.findIndex((todo: Email) => todo.id === todoId);
      this.inbox.splice(index, 1);
    },
  },
});
