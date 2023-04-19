<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { faker } from "@faker-js/faker";
const generateMessage = () => {
  return {
    avatar: faker.internet.avatar(),
    message: faker.lorem.text(),
  };
};

const list = ref(
  Array.from({ length: 20000 }, (value, index) => ({
    id: index + "",
    ...generateMessage(),
  }))
);
</script>

<template>
  <v-card height="900" elevation="6"> </v-card>
</template>

<style scoped lang="scss"></style>
