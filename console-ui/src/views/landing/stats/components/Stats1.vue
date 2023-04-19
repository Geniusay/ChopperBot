<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
const items = [
  {
    title: "1.9s",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse expedita fugit.",
    subTitle: "0-60 mph",
    icon: "mdi-clock-outline",
  },
  {
    title: "+250mph",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse expedita fugit.",
    subTitle: "Top Speed",
    icon: "mdi-clock-outline",
  },
  {
    title: "620mi",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse expedita fugit.",
    icon: "mdi-clock-outline",
    subTitle: "Range",
  },
  {
    title: "10k Nm",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse expedita fugit.",
    icon: "mdi-clock-outline",
    subTitle: "Torque",
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Stats1</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-row align="center" justify="center">
        <v-col cols="6" md="4" lg="3" v-for="item in items">
          <v-card
            elevation="0"
            class="text-center pa-5 base-card text-center mx-auto d-flex flex-column justify-center"
          >
            <h1 class="text-h2 font-weight-black">{{ item.title }}</h1>
            <p class="py-5">
              {{ item.description }}
            </p>
            <h6>{{ item.subTitle }}</h6>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
