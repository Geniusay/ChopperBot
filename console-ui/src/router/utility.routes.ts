export default [
  {
    path: "/utility/maintenance",
    name: "utility-maintenance",
    component: () =>
      import(
        /* webpackChunkName: "utility-maintenance" */ "@/views/utility/MaintenancePage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "auth",
      title: "MaintenancePage",
    },
  },
  {
    path: "/utility/coming-soon",
    name: "utility-soon",
    component: () =>
      import(
        /* webpackChunkName: "utility-soon" */ "@/views/utility/SoonPage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "auth",
      title: "Coming Soon",
    },
  },
  {
    path: "/utility/help",
    name: "utility-help",
    component: () =>
      import(
        /* webpackChunkName: "utility-help" */ "@/views/utility/HelpPage.vue"
      ),
    meta: {
      requiresAuth: true,
      title: "Help",
      layout: "ui",
      category: "Utility",
    },
  },
];
