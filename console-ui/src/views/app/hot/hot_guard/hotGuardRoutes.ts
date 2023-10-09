export default [
  {
    path: "",
    redirect: "/apps/hotGuard/settings",
  },
  {
    path: "settings",
    name: "hot-guard-settings",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/app/hot/hot_guard/pages/HotSettingsPage.vue"
        ),
  },
  {
    path: "guards",
    name: "hot-guard-guards",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-completed" */ "@/views/app/hot/hot_guard/pages/HotGuardsPage.vue"
        ),
  },
];
