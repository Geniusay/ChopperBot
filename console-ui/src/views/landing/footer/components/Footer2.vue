<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { RouterLink } from "vue-router";

const navs = [
  {
    title: "Home",
    icon: "mdi-home",
    to: "/",
  },
  {
    title: "About Us",
    icon: "mdi-account",
    to: "/",
  },
  {
    title: "Services",
    icon: "mdi-account",
    to: "/",
  },
  {
    title: "Contact Us",
    icon: "mdi-account",
    to: "/",
  },
  // Team
  {
    title: "Team",
    icon: "mdi-account",
    to: "/",
  },
  // Blog
  {
    title: "Blog",
    icon: "mdi-account",
    to: "/",
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Footer2</span>
    </v-toolbar-title>
  </v-toolbar>

  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper"
    color="#F2F5F8"
    rounded
  >
    <v-container class="text-left pa-10">
      <v-sheet
        class="mx-auto"
        color="transparent"
        elevation="0"
        max-width="1600"
      >
        <v-row>
          <v-col cols="12" md="6">
            <img
              class="mt-2 mb-11"
              width="150"
              src="@/assets/logo_light.svg"
              alt=""
            />
            <p class="my-4">2000+ Our clients are subscribe Around the World</p>
          </v-col>
          <v-col cols="12" md="6">
            <div class="d-flex flex-wrap justify-center justify-md-end pb-5">
              <v-btn class="mx-3" color="blue-grey-darken-2" icon>
                <v-icon>mdi-facebook</v-icon></v-btn
              >
              <v-btn color="blue-grey-darken-2" icon>
                <v-icon>mdi-twitter</v-icon>
              </v-btn>
            </div>

            <div class="d-flex flex-wrap justify-center justify-md-end">
              <router-link
                class="text-primary mx-3 mb-3 font-weight-bold"
                v-for="nav in navs"
                to="nav.to"
              >
                {{ nav.title }}</router-link
              >
            </div>
          </v-col>
        </v-row>
        <hr class="my-3" />
        <p class="text-center my-5">Yang J.K. © All Rights Reserved</p>
      </v-sheet>
    </v-container>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
