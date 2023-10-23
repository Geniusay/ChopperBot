import unsplashRoutes from "@/views/app/unsplash/UnsplashRoutes";

import emailRoutes from "@/views/app/email/emailRoutes";
import taskRoutes from "@/views/app/taskcenter/taskRoutes";
import hotGuardRoutes from "@/views/app/hot/hot_guard/hotGuardRoutes";
import liveFollowRoutes from "@/views/app/hot/live_follow/liveFollowRoutes";
export default [
  {
    path: "/apps/board",
    name: "app-board",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/utility/BoardPage.vue"
      ),
    meta: {
      requiresAuth: true,
      title: "Board",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/creeperLibrary",
    name: "app-creeper-library",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/creeper/CreeperPage.vue"
        ),
    meta: {
      requiresAuth: true,
      title: "CreeperLibrary",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/taskCenter",
    name: "app-task-center",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/taskcenter/TaskCenterApp.vue"
        ),
    children: [...taskRoutes],
    meta: {
      requiresAuth: true,
      title: "Task Center",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/hotGuard",
    name: "app-hot-guard",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/hot/hot_guard/HotGuardView.vue"
        ),
    children: [...hotGuardRoutes],
    meta: {
      requiresAuth: true,
      title: "Hot Guard",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/heatRecommend",
    name: "app-heat-recommend",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/hot/heat_recommend/HeatRecommendationView.vue"
        ),
    meta: {
      requiresAuth: true,
      title: "Heat Recommend",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/liveFollow",
    name: "app-live-follow",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/hot/live_follow/LiveFollowView.vue"
        ),
    children: [...liveFollowRoutes],
    meta: {
      requiresAuth: true,
      title: "Live Follow",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/email",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Email",
    },
    component: () =>
      import(
        /* webpackChunkName: "app-email" */ "@/views/app/email/EmailApp.vue"
      ),
    children: [...emailRoutes],
  },
  {
    path: "/apps/barrageCurve",
    name: "app-barrage-curve",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/barrage/curve/BarrageCurveView.vue"
        ),
    meta: {
      requiresAuth: true,
      title: "Barrage Curve",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/label",
    name: "app-label-manage",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/app/label/LabelManageView.vue"
        ),
    meta: {
      requiresAuth: true,
      title: "Label Manage",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/nitori",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Nitori",
    },
    component: () =>
      import(
        /* webpackChunkName: "app-nitori" */ "@/views/app/nitori/NitoriApp.vue"
      ),
    children: [],
  },
  {
    path: "/apps/booking",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Booking",
    },
    component: () =>
      import(
        /* webpackChunkName: "app-booking" */ "@/views/app/booking/BookingApp.vue"
      ),
    children: [],
  },
  {
    path: "/apps/ikea",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Ikea",
    },
    component: () =>
      import(/* webpackChunkName: "app-ikea" */ "@/views/app/ikea/IkeaApp.vue"),
    children: [],
  },
  {
    path: "/apps/unsplash",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Photos",
    },
    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/unsplash/UnsplashApp.vue"
      ),
    children: [...unsplashRoutes],
  },
];
