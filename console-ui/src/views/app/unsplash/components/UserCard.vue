<!--
* @Component: PhotoCard
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useUnsplashStore } from "../unsplashStore";
import { User } from "../unsplashTypes";

const unsplashStore = useUnsplashStore();
const props = defineProps<{
  user: User;
}>();

const snackbar = reactive({
  isShow: false,
  timeout: 1000,
  text: "",
});
</script>

<template>
  <div class="">
    <v-card
      width="100%"
      class="user-card d-flex flex-column justify-space-between"
    >
      <div class="card-top bg-secondary-lighten-1 text-content">
        <v-avatar class="mr-5" size="avatarSize">
          <img :src="user.profile_image.small" alt="alt" />
        </v-avatar>
        <div class="flex-1">
          <h5>{{ user.name }}</h5>
          <h5>{{ user.username }}</h5>
        </div>
        <v-tooltip location="bottom" text="Add To Collection">
          <template v-slot:activator="{ props }">
            <v-btn v-bind="props" icon="mdi-plus"> </v-btn>
          </template>
        </v-tooltip>
      </div>

      <div class="d-flex px-2">
        <v-img
          v-if="user.photos.length > 0"
          height="100"
          cover
          :key="user.photos[0].id"
          :src="user.photos[0].urls.small"
        ></v-img>
        <v-img
          v-if="user.photos.length > 1"
          class="mx-1"
          height="100"
          cover
          :key="user.photos[1].id"
          :src="user.photos[1].urls.small"
        ></v-img>
        <v-img
          v-if="user.photos.length > 2"
          height="100"
          cover
          :key="user.photos[2].id"
          :src="user.photos[2].urls.small"
        ></v-img>
      </div>
      <v-card-actions>
        <v-tooltip location="bottom" text="Profile">
          <template v-slot:activator="{ props }">
            <v-btn
              color="primary"
              variant="flat"
              block
              v-bind="props"
              :to="`/apps/unsplash/user/${user.username}`"
            >
              Profile</v-btn
            >
          </template>
        </v-tooltip>
      </v-card-actions>
    </v-card>
  </div>
</template>

<style scoped lang="scss">
.user-card {
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;

  &:hover {
    box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 24px 0px !important;
  }
  .card-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2rem;
    font-size: 1rem;
  }
}
</style>
