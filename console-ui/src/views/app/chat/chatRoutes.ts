export default [
  {
    path: "",
    redirect: "/apps/chat/base-channel",
  },
  {
    path: "base-channel",
    component: () =>
      import(
        /* webpackChunkName: "apps-chat-channel" */ "@/views/app/chat/pages/ChatPage.vue"
      ),
  },
  //   {
  //     path: "channel/:id",
  //     name: "apps-chat-channel",
  //     component: () =>
  //       import(
  //         /* webpackChunkName: "apps-chat-channel" */ "@/views/app/chat/pages/ChatPage.vue"
  //       ),
  //   },
];
