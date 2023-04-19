export default [
  {
    path: "",
    redirect: "/apps/todo/tasks",
  },
  {
    path: "tasks",
    name: "apps-todo-tasks",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/app/todo/pages/TasksPage.vue"
      ),
  },
  {
    path: "completed",
    name: "apps-todo-completed",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-completed" */ "@/views/app/todo/pages/CompletedPage.vue"
      ),
  },
  {
    path: "label/:id",
    name: "apps-todo-label",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-label" */ "@/views/app/todo/pages/LabelPage.vue"
      ),
  },
];
