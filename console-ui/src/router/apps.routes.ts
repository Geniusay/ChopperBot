import unsplashRoutes from "@/views/app/unsplash/UnsplashRoutes";
import todoRoutes from "@/views/app/todo/todoRoutes";
import emailRoutes from "@/views/app/email/emailRoutes";
import chatRoutes from "@/views/app/chat/chatRoutes";
import taskRoutes from "@/views/app/taskcenter/taskRoutes";

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
    path: "/apps/chat",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Chat",
    },
    component: () =>
      import(/* webpackChunkName: "app-chat" */ "@/views/app/chat/ChatApp.vue"),
    children: [...chatRoutes],
  },
  {
    path: "/apps/todo",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Todo",
    },
    component: () =>
      import(/* webpackChunkName: "app-todo" */ "@/views/app/todo/TodoApp.vue"),
    children: [...todoRoutes],
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
