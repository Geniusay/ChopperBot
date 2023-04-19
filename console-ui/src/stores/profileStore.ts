import { defineStore } from "pinia";

export const useProfileStore = defineStore({
  id: "userProfile",
  state: () => ({
    basic: {
      username: "shirabako",
      realname: "yang J. K.",
      email: "yjkabko@gmail.com",
      avatar:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s",
      location: "Tokyo,Japan",
      role: "Admin",
      disabled: false,
      about: "Full Stack Developer",
      lastSignIn: "2023-03-29T01:11:13Z",
    },
    authorized: {
      google: false,
      facebook: false,
    },
    notifications: {
      officialEmails: true,
      followerUpdates: true,
    },
  }),
  actions: {
    getProfile() {
      return {
        basic: this.basic,
        authorized: this.authorized,
        notifications: this.notifications,
      };
    },

    // update Basic Info
    updateBasicInfo(info) {
      this.basic = { ...this.basic, ...info };
    },
    // update Authorized
    updateAuthorizedStatus(service, status) {
      this.authorized[service] = status;
    },
    // Update Notifications
    updateNotificationSettings(settings) {
      this.notifications = { ...this.notifications, ...settings };
    },
  },
});
