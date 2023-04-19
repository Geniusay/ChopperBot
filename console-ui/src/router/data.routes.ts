// users Data Page
export default [
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
