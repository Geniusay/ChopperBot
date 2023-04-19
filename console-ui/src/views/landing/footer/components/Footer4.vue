<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { RouterLink } from "vue-router";

const navs = [
  {
    title: "Product",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "Overview",
        to: "/",
      },
      {
        title: "Features",
        to: "/",
      },
      {
        title: "Pricing",
        to: "/",
      },
    ],
  },
  {
    title: "Resources",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "Documentation",
        to: "/",
      },
      {
        title: "News",
        to: "/",
      },
      {
        title: "FAQ",
        to: "/",
      },
    ],
  },
  {
    title: "Company",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "About us",
        to: "/",
      },
      {
        title: "Carrers",
        to: "/",
      },
      {
        title: "Press",
        to: "/",
      },
    ],
  },
  {
    title: "Contact",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "Contact us",
        to: "/",
      },
      {
        title: "Offices",
        to: "/",
      },
      {
        title: "Twitter",
        to: "/",
      },
    ],
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Footer4</span>
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
          <v-col cols="12" md="4">
            <img
              class="my-2"
              width="150"
              src="@/assets/logo_light.svg"
              alt=""
            />
            <p class="my-4">2000+ Our clients are subscribe Around the World</p>
          </v-col>
          <v-col cols="12" md="8">
            <v-row>
              <v-col cols="12" md="3" v-for="nav in navs">
                <p class="text-h6 mb-4">
                  <b>{{ nav.title }}</b>
                </p>
                <p
                  class="text-body-2 text-primary mb-2"
                  v-for="subNav in nav.subNavs"
                  :key="subNav.title"
                >
                  <router-link :to="nav.to">
                    {{ subNav.title }}
                  </router-link>
                </p>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <hr class="my-3" />
        <p class="text-center text-body-2 py-5">
          <span>© INDIELAYER 2021</span>
          <a class="text-blue-grey mx-5" href="#">PRIVACY POLICY</a>
          <a class="text-blue-grey" href="#">TEARMS OF SERVICE</a>
        </p>
      </v-sheet>
    </v-container>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
