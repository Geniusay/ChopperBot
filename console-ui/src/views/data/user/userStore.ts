import { defineStore } from "pinia";
import { User } from "./userTypes";

const users = [

];

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    userList: users,
    currentLabel: "sad",
    labels: [

    ],
  }),

  // persist: {
  //   enabled: true,
  //   strategies: [
  //     {
  //       storage: localStorage,
  //       paths: ["userList"],
  //     },
  //   ],
  // },

  getters: {
    // Full list of users
    getUserList() {
      return this.userList.filter((user: User) => !user.completed);
    },

    // Completed users
    getCompletedUsers() {
      return this.userList.filter((user: User) => user.completed);
    },

    // Specific Label users
    getLabelUsers() {
      return this.userList.filter(
        (user: User) =>
          user.typeList  && user.typeList.some(item => item.type === this.currentLabel) && !user.completed
      );
    },
  },
  actions: {
    // Add new user
    addNewUser(user: User) {
      user.uid = "_" + Math.random().toString(36).substring(2, 11);
      this.userList.push(user);
    },
    // update user
    updateUser(user: User) {
      const index = this.userList.findIndex((item: User) => item.uid === user.uid);
      this.userList.splice(index, 1, user);
    },

    // Delete user By Id
    deleteUserById(userId: string) {
      const index = this.userList.findIndex((user: User) => user.uid === userId);
      this.userList.splice(index, 1);
    },
  },
});
