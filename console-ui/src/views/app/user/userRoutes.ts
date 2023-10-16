export default [
  {
    path: "",
    redirect: "/data/account/tasks",
  },
  {
    path: "tasks",
    name: "apps-todo-tasks",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/app/user/pages/TasksPage.vue"
      ),
  },
  {
    path: "completed",
    name: "apps-todo-completed",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-completed" */ "@/views/app/user/pages/CompletedPage.vue"
      ),
  },
  {
    path: "label/:id",
    name: "apps-todo-label",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-label" */ "@/views/app/user/pages/LabelPage.vue"
      ),
  },
];
