export default [
  {
    path: "",
    // redirect: "photos",
    component: () =>
      import(
        /* webpackChunkName: "unsplash-photos" */ "@/views/app/unsplash/PhotosPage.vue"
      ),
  },
  {
    path: "user/:username",
    name: "unsplash-user",
    component: () =>
      import(
        /* webpackChunkName: "unsplash-user" */ "@/views/app/unsplash/UserPage.vue"
      ),
  },
  {
    path: "collection/:id",
    name: "unsplash-collection",
    component: () =>
      import(
        /* webpackChunkName: "unsplash-collection" */ "@/views/app/unsplash/CollectionPage.vue"
      ),
  },
  {
    path: "my-page",
    name: "unsplash-my-page",
    component: () =>
      import(
        /* webpackChunkName: "unsplash-my-page" */ "@/views/app/unsplash/MyPage.vue"
      ),
  },
];
