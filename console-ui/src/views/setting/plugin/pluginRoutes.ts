export default [
  {
    path: "",
    redirect: "/setting/plugin_setting/plugins",
  },
  {
    path: "plugins",
    name: "setting-plugin-plugins",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/setting/plugin/pages/PluginPage.vue"
        ),
  },
  {
    path: "running",
    name: "setting-plugin-running",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-completed" */ "@/views/setting/plugin/pages/RunningPage.vue"
        ),
  },
  {
    path: "autoStart",
    name: "setting-plugin-autoStart",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-label" */ "@/views/setting/plugin/pages/AutoStartPage.vue"
        ),
  },
];
