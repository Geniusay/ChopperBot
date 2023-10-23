// users Data Page
import userRoutes from "@/views/data/user/userRoutes";
export default [
  {
    path: "/data/hotLive",
    name: "data-hot-live",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/data/hot_live/HotLiveView.vue"
        ),
    meta: {
      title: "Hot Live",
      layout: "ui",
      category: "data",
    },
  },

  {
    path: "/data/account",
    name: "data-account",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "data",
      title: "Account",
    },
    component: () =>
      import(/* webpackChunkName: "app-todo" */ "@/views/data/user/UserApp.vue"),
    children: [...userRoutes],
  },
  {
    path: "/data/users-data",
    component: () => import("@/views/datatable/UsersDataPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Data",
      title: "UsersDataTable",
    },
  },
  // photos Data Page
  {
    path: "/data/photos-data",
    component: () => import("@/views/datatable/PhotosDataPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Data",
      title: "PhotosDataTable",
    },
  },
  // collections Data Page
  {
    path: "/data/collections-data",
    component: () => import("@/views/datatable/CollectionsDataPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Data",
      title: "CollectionsDataTable",
    },
  },
  // topics Data Page
  {
    path: "/data/topics-data",
    component: () => import("@/views/datatable/TopicsDataPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Data",
      title: "TopicsDataTable",
    },
  },
  // topic Photos Data Page
  {
    path: "/data/topic-photos-data",
    component: () => import("@/views/datatable/TopicPhotosDataPage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Data",
      title: "TopicPhotosDataTable",
    },
  },
];
