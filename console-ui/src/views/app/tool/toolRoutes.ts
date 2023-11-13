export default [
  {
    path: "",
    redirect: "/apps/tool/video",
  },
  {
    path: "video",
    name: "apps-tool-video",
    component: () =>
      import(
        /* webpackChunkName: "apps-tool-video" */ "@/views/app/tool/pages/VideoTool.vue"
        ),
  },

];
