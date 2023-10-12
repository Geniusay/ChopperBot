export default [
  {
    path: "",
    redirect: "/apps/liveFollow/page",
  },
  {
    path: "page",
    name: "live-follow-page",
    component: () =>
      import(
        /* webpackChunkName: "apps-todo-tasks" */ "@/views/app/hot/live_follow/pages/LiveFollowPage.vue"
        ),
  },
];
