// users Data Page
import pluginRoutes from "@/views/setting/plugin/pluginRoutes";

export default [
  {
    path: "/setting/plugin_setting",
    component: () => import("@/views/setting/plugin/PluginApp.vue"),
    children: [...pluginRoutes],
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "AI",
      title: "Plugin Setting",
    },
  },
];
