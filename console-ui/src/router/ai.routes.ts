// users Data Page
export default [
  {
    path: "/ai/chatbot_v1",
    component: () => import("@/views/chatgpt/ChatBotV1.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "AI",
      title: "ChatBotV1",
    },
  },
  {
    path: "/ai/chatbot_v2",
    component: () => import("@/views/chatgpt/ChatBotV2.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "AI",
      title: "ChatBotV2",
    },
  },
];
