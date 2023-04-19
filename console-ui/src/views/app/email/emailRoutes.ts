export default [
  {
    path: "",
    redirect: "/apps/email/inbox",
  },
  {
    path: "inbox",
    name: "apps-email-inbox",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-inbox" */ "@/views/app/email/pages/InboxPage.vue"
      ),
  },
  {
    path: "send",
    name: "apps-email-send",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-send" */ "@/views/app/email/pages/SendPage.vue"
      ),
  },
  {
    path: "drafts",
    name: "apps-email-drafts",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-drafts" */ "@/views/app/email/pages/DraftsPage.vue"
      ),
  },
  {
    path: "starred",
    name: "apps-email-starred",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-starred" */ "@/views/app/email/pages/StarredPage.vue"
      ),
  },
  {
    path: "trash",
    name: "apps-email-trash",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-trash" */ "@/views/app/email/pages/TrashPage.vue"
      ),
  },
  {
    path: "inbox/:id",
    name: "apps-email-view",
    component: () =>
      import(
        /* webpackChunkName: "apps-email-view" */ "@/views/app/email/pages/ViewPage.vue"
      ),
  },
];
