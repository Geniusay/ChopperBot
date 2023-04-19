<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { BASE_URL, config } from "./unsplashConfig";
import axios, { AxiosResponse } from "axios";
const route = useRoute();

const collectionUrl = computed(() => {
  return BASE_URL + "collections/" + route.params.id;
});

const collectionPhotosUrl = computed(() => {
  return BASE_URL + "collections/" + route.params.id + "/photos";
});

const collectionSimilarUrl = computed(() => {
  return BASE_URL + "collections/" + route.params.id + "/similar";
});

const isLoading = ref(false);

const initData = async () => {
  isLoading.value = true;
  await getCollection();
  await getCollectionPhotos();
  await getCollectionSimilar();
  isLoading.value = false;
};

const getCollection = async () => {
  await axios
    .get(collectionUrl.value, config)
    .then((response: AxiosResponse) => {
      console.log(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
};

const getCollectionPhotos = async () => {
  await axios
    .get(collectionPhotosUrl.value, config)
    .then((response: AxiosResponse) => {
      console.log(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
};

const getCollectionSimilar = async () => {
  await axios
    .get(collectionSimilarUrl.value, config)
    .then((response: AxiosResponse) => {
      console.log(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
};

initData();
</script>

<template>
  <div class="">{{ route.params.id }}</div>
</template>

<style scoped lang="scss"></style>
