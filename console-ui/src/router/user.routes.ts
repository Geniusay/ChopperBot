export default [
  {
    path: "/users",
    redirect: "users-list",
  },
  {
    path: "/users/list",
    name: "users-list",
    component: () =>
      import(
        /* webpackChunkName: "users-list" */ "@/views/users/UsersPage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      title: "User List",
    },
  },
  {
    path: "/users/edit",
    name: "users-edit",
    component: () =>
      import(
        /* webpackChunkName: "users-edit" */ "@/views/users/EditUserPage.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      title: "Edit User",
    },
  },
  {
    path: "/profile",
    name: "profile",
    component: () =>
      import(/* webpackChunkName: "profile" */ "@/views/users/ProfilePage.vue"),
    meta: {
      requiresAuth: true,
      layout: "ui",
      title: "Profile",
      category: "Config",
    },
  },
];
