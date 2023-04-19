<!--
* @Component: User
* @Maintainer: J.K. Yang
* @Description: Page Of User 
-->
<script setup lang="ts">
import { BASE_URL, config } from "./unsplashConfig";
import axios from "axios";
import { useUnsplashStore } from "./unsplashStore";
import PhotoCard from "./components/PhotoCard.vue";
import CollectionCard from "./components/CollectionCard.vue";
const unsplashStore = useUnsplashStore();
const route = useRoute();
const userProfileUrl = computed(() => {
  return BASE_URL + "users/" + route.params.username;
});

const userPhotosUrl = computed(() => {
  return BASE_URL + "users/" + route.params.username + "/photos";
});

const userLikesUrl = computed(() => {
  return BASE_URL + "users/" + route.params.username + "/likes";
});

const userCollectionsUrl = computed(() => {
  return BASE_URL + "users/" + route.params.username + "/collections";
});

const userProfileData = ref(null);
const userPhotosData = ref(null);
const userLikesData = ref([]);
const userCollectionsData = ref([]);
const tab = ref(null);

const isLoading = ref(false);

const initData = async () => {
  isLoading.value = true;
  const userProfileResponse = await axios.get(userProfileUrl.value, config);
  const userPhotosResponse = await axios.get(userPhotosUrl.value, config);
  const userLikesResponse = await axios.get(userLikesUrl.value, config);
  const userCollectionsResponse = await axios.get(
    userCollectionsUrl.value,
    config
  );
  userPhotosData.value = userPhotosResponse.data;
  userProfileData.value = userProfileResponse.data;
  userLikesData.value = userLikesResponse.data;
  userCollectionsData.value = userCollectionsResponse.data;
  isLoading.value = false;
};

const snackbar = reactive({
  isShow: false,
  timeout: 1000,
  text: "",
});

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

initData();
</script>

<template>
  <v-sheet v-if="userProfileData" class="profile-sheet">
    <v-container class="profile-container">
      <v-row>
        <v-col cols="12" md="3" class="pr-5">
          <v-img
            class="mx-auto"
            width="200"
            style="border-radius: 50%"
            :src="userProfileData.profile_image.large"
          ></v-img>
        </v-col>
        <v-col cols="12" md="9">
          <h1 class="text-h3 font-weight-bold">
            {{ userProfileData.username }}
          </h1>
          <p class="my-5">
            Download free, beautiful high-quality photos curated by
            <b> {{ userProfileData.first_name }}</b>
          </p>
          <p v-if="userProfileData.location">
            <v-icon>mdi-map-marker</v-icon>{{ userProfileData.location }}
          </p>

          <p class="mb-3">Interests</p>
          <div>
            <v-chip
              class="interest-chip ma-2"
              color="primary"
              label
              v-for="item in userProfileData.tags.aggregated"
              :key="item.title"
            >
              <v-icon start icon="mdi-bookmark-outline"></v-icon>
              {{ item.title }}
            </v-chip>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>

  <v-sheet class="mt-5 shadow-1">
    <v-tabs
      color="primary"
      v-model="tab"
      bg-color="transparent"
      sliderColor="primary"
    >
      <v-tab value="photos">
        <v-icon class="mr-2">mdi-image</v-icon>Photos</v-tab
      >
      <v-tab value="likes"> <v-icon class="mr-2">mdi-heart</v-icon>Likes</v-tab>
      <v-tab value="collections">
        <v-icon class="mr-2">mdi-image</v-icon>Collections</v-tab
      >
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="photos">
          <v-sheet v-if="userLikesData.length > 0" min-height="80vh">
            <v-row> </v-row>
          </v-sheet>
          <v-sheet
            v-else
            min-height="80vh"
            class="d-flex align-center justify-center"
          >
            <v-img
              src="https://unsplash-assets.imgix.net/empty-states/photos.png"
              height="400"
            ></v-img>
          </v-sheet>
        </v-window-item>
        <v-window-item value="likes">
          <v-sheet v-if="userLikesData.length > 0" min-height="80vh">
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
                lg="3"
                v-for="photo in userLikesData"
                :key="photo.id"
              >
                <PhotoCard :photo="photo"></PhotoCard>
              </v-col>
            </v-row>
          </v-sheet>
          <v-sheet
            v-else
            min-height="80vh"
            class="d-flex align-center justify-center"
          >
            <v-img
              src="https://unsplash-assets.imgix.net/empty-states/photos.png"
              height="400"
            ></v-img>
          </v-sheet>
        </v-window-item>
        <v-window-item value="collections" class="pa-1">
          <v-sheet v-if="userCollectionsData.length > 0" min-height="80vh">
            <v-row>
              <v-col
                cols="12"
                sm="6"
                lg="4"
                v-for="collection in userCollectionsData"
                :key="collection.id"
              >
                <CollectionCard :collection="collection"></CollectionCard>
              </v-col>
            </v-row>
          </v-sheet>
          <v-sheet
            v-else
            min-height="80vh"
            class="d-flex align-center justify-center"
          >
            <v-img
              src="https://unsplash-assets.imgix.net/empty-states/photos.png"
              height="400"
            ></v-img>
          </v-sheet>
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-sheet>
</template>

<style scoped lang="scss">
.interest-chip {
  cursor: pointer;
  transition: all 0.3s;
  &:hover {
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
    transition: all 0.3s;
  }
}

.photo-card {
  display: flex;
  flex-direction: column;
  background-color: rgba(0, 0, 0, 0.2);
  height: 100%;
  padding: 1rem;
  opacity: 0;
  cursor: zoom-in;
  &:hover {
    opacity: 1;
  }

  .card-top,
  .card-bottom {
    display: flex;
    align-items: center;
  }
}

.profile-container {
  margin: 0 auto;
  padding: 100px 0px;
  max-width: 1600px;
  background-color: rgab(0, 0, 0, 0);
}
</style>
