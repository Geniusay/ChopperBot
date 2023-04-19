<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: Imitate Nitori App
-->
<script setup lang="ts">
import FavoriteCard from "./components/FavoriteCard.vue";
import PhotoDetail from "./PhotoDetailModal.vue";
import UserCard from "./components/UserCard.vue";
import { Icon, listIcons } from "@iconify/vue";
import axios from "axios";
import { useAxios } from "@vueuse/integrations/useAxios";
import { useUnsplashStore } from "./unsplashStore";
import { BASE_URL, ACCESS_KEY, config } from "./unsplashConfig";
const unsplashStore = useUnsplashStore();
const router = useRouter();

interface SearchParams {
  url: string;
  query: string;
  perPage: number;
  page: number;
}

const searchParams: SearchParams = reactive({
  url: BASE_URL + "search?",
  query: "blue",
  perPage: 20,
  page: 1,
});

const photoParams: SearchParams = reactive({
  url: BASE_URL + "search/photos?",
  query: "blue",
  perPage: 20,
  page: 1,
});

const collectionParams: SearchParams = reactive({
  url: BASE_URL + "search/collections?",
  query: "blue",
  perPage: 20,
  page: 1,
});

const userParams: SearchParams = reactive({
  url: BASE_URL + "search/users?",
  query: "blue",
  perPage: 20,
  page: 1,
});

const photoData = reactive({
  photos: [],
  total: 0,
  totalPages: 0,
});

const collectionData = reactive({
  collections: [],
  total: 0,
  totalPages: 0,
});

const userData = reactive({
  users: [],
  total: 0,
  totalPages: 0,
});

const relatedSearches = ref([]);

const tab = ref(null);

const search = async () => {
  const response = await axios.get(
    `${searchParams.url}page=${searchParams.page}&per_page=${searchParams.perPage}&query=${searchParams.query}`,
    config
  );

  // Photos
  photoData.photos = response.data.photos.results;
  photoData.total = response.data.photos.total;
  photoData.totalPages = response.data.photos.total_pages;

  // Collections
  collectionData.collections = response.data.collections.results;
  collectionData.total = response.data.collections.total;
  collectionData.totalPages = response.data.collections.total_pages;

  // Users
  userData.users = response.data.users.results;
  userData.total = response.data.users.total;
  userData.totalPages = response.data.users.total_pages;

  // RelatedSearches
  relatedSearches.value = response.data.related_searches;
  initData();
};

onMounted(() => {
  search();
});

const initData = () => {
  photoData.photos.forEach((photo) => {
    photo.liked_by_user = unsplashStore.favoriteList.some(
      (item) => item.id === photo.id
    );
  });
};

const morePhotos = async () => {
  photoParams.page++;
  const response = await axios.get(
    `${photoParams.url}page=${photoParams.page}&per_page=${photoParams.perPage}&query=${photoParams.query}`,
    config
  );
  photoData.photos.push(...response.data.results);
};

const moreCollections = async () => {
  collectionParams.page++;
  const response = await axios.get(
    `${collectionParams.url}page=${collectionParams.page}&per_page=${collectionParams.perPage}&query=${collectionParams.query}`,
    config
  );
  collectionData.collections.push(...response.data.results);
};

const moreUsers = async () => {
  userParams.page++;
  const response = await axios.get(
    `${userParams.url}page=${userParams.page}&per_page=${userParams.perPage}&query=${userParams.query}`,
    config
  );
  userData.users.push(...response.data.results);
};

const searchRelated = (query: string) => {
  searchParams.query = query;
  photoParams.query = query;
  collectionParams.query = query;
  userParams.query = query;
  search();
};

const snackbar = reactive({
  isShow: false,
  timeout: 1000,
  text: "",
});

const toggleLike = (item) => {
  if (!item.liked_by_user) {
    snackbar.text = "Added to your favorite";
    snackbar.isShow = true;
    unsplashStore.addToFavorite(item);
    item.likes++;
  } else {
    snackbar.text = "Removed from your favorite";
    snackbar.isShow = true;
    unsplashStore.removeFromFavorite(item);
    item.likes--;
  }
  item.liked_by_user = !item.liked_by_user;
};

const downloadPhoto = (photo) => {
  const a = document.createElement("a");
  a.href = photo.links.download + "&force=true";
  a.download = photo.id + ".jpg";
  a.click();
  snackbar.text = "Downloading now, please wait";
  snackbar.timeout = 2000;
  snackbar.isShow = true;
  snackbar.timeout = 1000;
};

const photoDialog = ref(false);
const photoId = ref("");
const openPhotoDialog = (id: string) => {
  photoId.value = id;
  photoDialog.value = true;
};
</script>

<template>
  <div class="photo-page">
    <v-toolbar color="primary">
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-text-field
        v-model="searchParams.query"
        hide-details
        prepend-inner-icon="mdi-magnify"
        single-line
        placeholder="Search photos"
        @keyup.enter="search"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn color="">Go</v-btn>
    </v-toolbar>
    <v-row>
      <v-col cols="12" xl="10">
        <v-card class="mt-2">
          <v-tabs v-model="tab" bg-color="primary">
            <v-tab value="photos"
              ><v-icon class="mr-2">mdi-image-outline</v-icon> photos
              <span class="ml-2">({{ photoData.total }})</span></v-tab
            >
            <v-tab value="collections">
              collections<span class="ml-2"
                >({{ collectionData.total }})</span
              ></v-tab
            >
            <v-tab value="users"
              ><v-icon class="mr-2">mdi-account-multiple</v-icon> users<span
                class="ml-2"
                >({{ userData.total }})</span
              ></v-tab
            >
          </v-tabs>

          <v-card-text>
            <v-window v-model="tab">
              <!-- Photos -->
              <v-window-item value="photos">
                <v-card
                  min-height="80vh"
                  class="pa-5 d-flex align-center justify-center"
                  v-if="photoData.total === 0"
                >
                  <v-img
                    src="https://unsplash-assets.imgix.net/empty-states/photos.png"
                    height="400"
                  ></v-img>
                </v-card>
                <v-card min-height="80vh" class="pa-5" v-else>
                  <v-row>
                    <v-slide-group show-arrows>
                      <v-slide-group-item
                        v-for="item in relatedSearches"
                        :key="item.title"
                        v-slot="{ isSelected }"
                      >
                        <v-btn
                          class="ma-2"
                          rounded
                          :color="isSelected ? 'primary' : undefined"
                          @click="searchRelated(item.title)"
                        >
                          {{ item.title }}
                        </v-btn>
                      </v-slide-group-item>
                    </v-slide-group>
                  </v-row>
                  <v-row>
                    <v-col
                      cols="12"
                      xl="2"
                      lg="3"
                      md="4"
                      sm="6"
                      v-for="item in photoData.photos"
                      :key="item.id"
                    >
                      <v-card
                        width="100%"
                        class="info-card photo-card d-flex flex-column"
                        height="480"
                      >
                        <div>
                          <v-img
                            class="align-end text-white"
                            :src="item.urls.small"
                            :lazy-src="item.urls.small"
                            height="300"
                            cover
                            @click="openPhotoDialog(item.id)"
                          >
                            <template v-slot:placeholder>
                              <v-row
                                class="fill-height ma-0"
                                align="center"
                                justify="center"
                              >
                                <v-progress-circular
                                  indeterminate
                                  color="grey-lighten-5"
                                ></v-progress-circular>
                              </v-row>
                            </template>
                            <v-card-title class="card-title">
                              <v-avatar size="avatarSize" class="mr-2">
                                <img
                                  :src="item.user.profile_image.small"
                                  alt="alt"
                                />
                              </v-avatar>
                              {{ item.user.username }}</v-card-title
                            >
                          </v-img>
                        </div>

                        <v-card-subtitle class="mt-5">
                          <div>
                            size: height:{{ item.height }} width:{{
                              item.width
                            }}
                          </div>
                        </v-card-subtitle>

                        <v-card-text>
                          {{ item.alt_description }}
                          <div>{{ item.download_url }}</div>
                        </v-card-text>

                        <v-card-actions>
                          <v-btn @click="toggleLike(item)">
                            <v-icon
                              v-if="item.liked_by_user"
                              start
                              color="pink"
                              icon="mdi-heart"
                              class="heartBeat"
                            ></v-icon>
                            <v-icon
                              v-else
                              start
                              icon="mdi-heart-outline"
                            ></v-icon>
                            Like({{ item.likes }})
                            <v-tooltip
                              activator="parent"
                              location="bottom"
                              class=""
                              :text="item.liked_by_user ? 'Liked' : 'Like'"
                            ></v-tooltip>
                          </v-btn>
                          <v-spacer></v-spacer>
                          <v-tooltip location="bottom" text="Download">
                            <template v-slot:activator="{ props }">
                              <v-btn
                                v-bind="props"
                                icon="mdi-download"
                                @click="downloadPhoto(item)"
                              >
                              </v-btn>
                            </template>
                          </v-tooltip>
                          <v-tooltip location="bottom" text="Add To Collection">
                            <template v-slot:activator="{ props }">
                              <v-btn v-bind="props" icon="mdi-plus"> </v-btn>
                            </template>
                          </v-tooltip>
                        </v-card-actions>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-btn
                    v-if="photoParams.page < photoData.totalPages"
                    color=""
                    class="gradient info mt-5"
                    block
                    size="large"
                    @click="morePhotos"
                    >More Photos...</v-btn
                  >
                </v-card>
              </v-window-item>

              <!-- Collections -->
              <v-window-item value="collections">
                <v-card
                  min-height="80vh"
                  class="pa-5 d-flex align-center justify-center"
                  v-if="collectionData.total === 0"
                >
                  <v-img
                    src="https://unsplash-assets.imgix.net/empty-states/photos.png"
                    height="400"
                  ></v-img>
                </v-card>
                <v-card min-height="80vh" class="pa-5" v-else>
                  <v-row>
                    <v-slide-group show-arrows>
                      <v-slide-group-item
                        v-for="item in relatedSearches"
                        :key="item.title"
                        v-slot="{ isSelected }"
                      >
                        <v-btn
                          class="ma-2"
                          rounded
                          :color="isSelected ? 'primary' : undefined"
                          @click="searchRelated(item.title)"
                        >
                          {{ item.title }}
                        </v-btn>
                      </v-slide-group-item>
                    </v-slide-group>
                  </v-row>
                  <v-row>
                    <v-col
                      cols="12"
                      lg="6"
                      xl="4"
                      v-for="item in collectionData.collections"
                      :key="item.id"
                    >
                      <v-card
                        class="info-card collection-card d-flex mt-5"
                        color="secondary-lighten-1"
                      >
                        <v-img
                          max-width="200"
                          aspect-ratio="1"
                          cover
                          :src="item.cover_photo.urls.small"
                          :lazy-src="item.cover_photo.urls.small"
                          @click="
                            router.push({
                              name: 'unsplash-collection',
                              params: {
                                id: item.id,
                              },
                            })
                          "
                        >
                        </v-img>

                        <div class="pa-2 flex-1">
                          <v-card-title>
                            {{ item.title }}
                          </v-card-title>
                          <v-card-subtitle class="pt-4">
                            <v-avatar size="avatarSize">
                              <img
                                :src="item.user.profile_image.small"
                                alt="alt"
                              />
                            </v-avatar>
                            {{ item.user.username }}
                          </v-card-subtitle>
                          <v-card-text>
                            <div>{{ item.description }}</div>
                          </v-card-text>
                          <v-card-actions>
                            <span>{{ item.published_at }}</span>
                            <v-spacer></v-spacer>
                            <span class="text-accent">{{
                              item.total_photos
                            }}</span>
                          </v-card-actions>
                        </div>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-btn
                    v-if="collectionParams.page < collectionData.totalPages"
                    color=""
                    class="gradient info mt-5"
                    block
                    size="large"
                    @click="moreCollections"
                    >More Collections...</v-btn
                  >
                </v-card>
              </v-window-item>

              <!-- Users -->
              <v-window-item value="users">
                <v-card
                  min-height="80vh"
                  class="pa-5 d-flex align-center justify-center"
                  v-if="userData.total === 0"
                >
                  <v-img
                    src="https://unsplash-assets.imgix.net/empty-states/photos.png"
                    height="400"
                  ></v-img>
                </v-card>
                <v-card min-height="80vh" class="pa-5" v-else>
                  <v-row>
                    <v-slide-group show-arrows>
                      <v-slide-group-item
                        v-for="item in relatedSearches"
                        :key="item.title"
                        v-slot="{ isSelected }"
                      >
                        <v-btn
                          class="ma-2"
                          rounded
                          :color="isSelected ? 'primary' : undefined"
                          @click="searchRelated(item.title)"
                        >
                          {{ item.title }}
                        </v-btn>
                      </v-slide-group-item>
                    </v-slide-group>
                  </v-row>
                  <v-row>
                    <v-col
                      cols="12"
                      lg="3"
                      md="4"
                      sm="6"
                      v-for="item in userData.users"
                      :key="item.id"
                    >
                      <v-card
                        width="100%"
                        class="info-card user-card d-flex flex-column justify-space-between"
                      >
                        <div
                          class="card-top bg-secondary-lighten-1 text-content"
                        >
                          <v-avatar class="mr-5" size="avatarSize">
                            <img :src="item.profile_image.small" alt="alt" />
                          </v-avatar>
                          <div class="flex-1">
                            <h5>{{ item.name }}</h5>
                            <h5>{{ item.username }}</h5>
                          </div>
                          <v-tooltip location="bottom" text="Add To Collection">
                            <template v-slot:activator="{ props }">
                              <v-btn v-bind="props" icon="mdi-plus"> </v-btn>
                            </template>
                          </v-tooltip>
                        </div>

                        <v-card-actions>
                          <v-tooltip location="bottom" text="Add To Collection">
                            <template v-slot:activator="{ props }">
                              <v-btn
                                color="primary"
                                variant="flat"
                                block
                                v-bind="props"
                                :to="`/apps/unsplash/user/${item.username}`"
                              >
                                Profile</v-btn
                              >
                            </template>
                          </v-tooltip>
                        </v-card-actions>
                      </v-card>
                    </v-col>
                  </v-row>
                  <v-btn
                    v-if="userParams.page < userData.totalPages"
                    color=""
                    class="gradient info mt-5"
                    block
                    size="large"
                    @click="moreUsers"
                    >More Users...</v-btn
                  >
                  <v-row>
                    <v-col
                      cols="12"
                      lg="4"
                      sm="6"
                      v-for="item in userData.users"
                      :key="item.id"
                    >
                      <UserCard :user="item" />
                    </v-col>
                  </v-row>
                </v-card>
              </v-window-item>
            </v-window>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="0" xl="2">
        <FavoriteCard />
      </v-col>
    </v-row>

    <!-- SnackBar -->
    <v-snackbar v-model="snackbar.isShow" :timeout="snackbar.timeout">
      {{ snackbar.text }}
      <template v-slot:actions>
        <v-btn color="blue" variant="text" @click="snackbar.isShow = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>

    <v-dialog v-model="photoDialog">
      <PhotoDetail :photoId="photoId" />
    </v-dialog>
  </div>
</template>

<style scoped lang="scss">
.card-title {
  background-color: rgba(0, 0, 0, 0.3);
}

.info-card {
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
  &:hover {
    box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 24px 0px !important;
  }
}

.user-card {
  .card-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2rem;
    font-size: 1rem;
  }
}
</style>
