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
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/data/user/pages/TasksPage.vue"
      ),
  },
  {
    path: "completed",
    name: "apps-todo-completed",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-completed" */ "@/views/data/user/pages/CompletedPage.vue"
      ),
  },
  {
    path: "label/:id",
    name: "apps-todo-label",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-label" */ "@/views/data/user/pages/LabelPage.vue"
      ),
  },
];
