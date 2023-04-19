export default [
  // data table
  {
    path: "/ui/data-table",
    name: "ui-data-table",
    component: () =>
      import(
        /* webpackChunkName: "ui-data-table" */ "@/views/ui/DataTablePage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "DataTable",
    },
  },
  {
    path: "/ui/colors",
    name: "ui-colors",
    component: () =>
      import(/* webpackChunkName: "ui-colors" */ "@/views/ui/ColorsPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "Colors",
    },
  },
  {
    path: "/ui/gradient",
    name: "ui-gradient",
    component: () =>
      import(
        /* webpackChunkName: "ui-gradient" */ "@/views/ui/GradientPage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "Gradients",
    },
  },
  {
    path: "/ui/card",
    name: "ui-card",
    component: () =>
      import(/* webpackChunkName: "ui-card" */ "@/views/ui/CardPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "CardPage",
    },
  },
  {
    path: "/ui/grids",
    name: "ui-grids",
    component: () =>
      import(/* webpackChunkName: "ui-grids" */ "@/views/ui/GridsPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "Grids",
    },
  },
  {
    path: "/ui/scrollbar",
    name: "ui-scrollbar",
    component: () =>
      import(
        /* webpackChunkName: "ui-scrollbar" */ "@/views/ui/PerfectScrollbar.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "ScrollBar",
    },
  },
  {
    path: "/ui/water-fall",
    name: "ui-water-fall",
    component: () =>
      import(
        /* webpackChunkName: "ui-water-fall" */ "@/views/ui/WaterFall.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "WaterFall",
    },
  },
  {
    path: "/ui/masonry",
    name: "ui-masonry",
    component: () =>
      import(/* webpackChunkName: "ui-masonry" */ "@/views/ui/Masonry.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "Masonry",
    },
  },
  {
    path: "/ui/virtual-list",
    name: "ui-virtual-list",
    component: () =>
      import(
        /* webpackChunkName: "ui-virtual-list" */ "@/views/ui/VirtualList.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "VirtualList",
    },
  },
  {
    path: "/ui/virtual-scroller",
    name: "ui-virtual-scroller",
    component: () =>
      import(
        /* webpackChunkName: "ui-virtual-scroller" */ "@/views/ui/VirtualScroller.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "VirtualScroller",
    },
  },
  {
    path: "/playground",
    name: "ui-playground",
    component: () =>
      import(
        /* webpackChunkName: "ui-playground" */ "@/views/playground/PlaygroundPage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "UI",
      title: "Playground",
    },
  },
];
