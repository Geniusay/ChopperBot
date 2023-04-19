<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useUnsplashStore } from "..//unsplashStore";
const unsplashStore = useUnsplashStore();

const showAdd = ref(false);

const removePhoto = (photo) => {
  unsplashStore.removeFromFavorite(photo);
};
</script>

<template>
  <v-card class="mt-2 my-favorite" height="80vh">
    <v-toolbar class="pl-5" density="compact" color="primary">
      <span> My favorite </span>
      <v-spacer></v-spacer>
      <v-btn icon variant="text">
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-toolbar>

    <div
      class="favorite-container pa-3"
      v-if="unsplashStore.favoriteList.length > 0"
    >
      <transition-group name="fade" class="">
        <!-- <v-card
          class="my-2"
          v-for="item in unsplashStore.favoriteList"
          :key="item.id"
        >
          {{ item.id }}{{ item.user.username }}
        </v-card> -->

        <v-card
          v-for="item in unsplashStore.favoriteList"
          :key="item.id"
          class="d-flex align-center favorite-card mb-3"
        >
          <div style="width: 100px">
            <v-img
              :aspect-ratio="1"
              width="100"
              cover
              :src="item.urls.thumb"
              :lazy-src="item.urls.thumb"
            >
            </v-img>
          </div>
          <div class="px-2 flex-1 two-line">
            {{ item.description || item.alt_description }}
          </div>
        </v-card>
      </transition-group>
    </div>

    <!-- <v-btn color="error" @click="deleteItem">delete</v-btn> -->
  </v-card>
</template>

<style scoped lang="scss">
.favorite-container {
  overflow-y: scroll;
  height: 100%;
}
.favorite-card {
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
  transition: all 0.5s;
  &:hover {
    box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 24px 0px !important;
    transition: all 0.5s;
    border-right: 5px solid #344767;
  }
}
</style>
