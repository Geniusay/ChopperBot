export default [
  {
    path: "",
    redirect: "/apps/taskCenter/tasks",
  },
  {
    path: "tasks",
    name: "task-center-tasks",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/app/taskcenter/pages/TaskCenterPage.vue"
        ),
  },
];
