<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
const articles = [
  {
    id: 1,
    title: "Rev",
    image:
      "https://images.unsplash.com/photo-1585829365295-ab7cd400c167?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8YXJ0aWNsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60",
    createAt: "4/22/2022",
    lastReadAt: "1/13/2023",
    author: "afisbburne0",
    content:
      "is a modular Vue UI kit to build and prototype your projects faster. It features rich and responsive layouts and components, is built with Vue CLI on top of Vuetify, and is pre-loaded with support for Vuex and Vue Router. Comes with a Vue and a Nuxt starter template.",
  },
  {
    id: 2,
    title: "Rev",
    image:
      "https://images.unsplash.com/photo-1476242906366-d8eb64c2f661?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGFydGljbGV8ZW58MHx8MHx8&auto=format&fit=crop&w=600&q=60",
    createAt: "7/12/2022",
    lastReadAt: "11/13/2022",
    author: "bbickerstasse1",
    content:
      "is a modular Vue UI kit to build and prototype your projects faster. It features rich and responsive layouts and components, is built with Vue CLI on top of Vuetify, and is pre-loaded with support for Vuex and Vue Router. Comes with a Vue and a Nuxt starter template.",
  },
  {
    id: 3,
    title: "Honorable",
    image:
      "https://images.unsplash.com/photo-1488190211105-8b0e65b80b4e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fGFydGljbGV8ZW58MHx8MHx8&auto=format&fit=crop&w=600&q=60",
    createAt: "6/21/2022",
    lastReadAt: "10/7/2022",
    author: "bruffy2",
    content:
      "is a modular Vue UI kit to build and prototype your projects faster. It features rich and responsive layouts and components, is built with Vue CLI on top of Vuetify, and is pre-loaded with support for Vuex and Vue Router. Comes with a Vue and a Nuxt starter template.",
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card1</span>
    </v-toolbar-title>
  </v-toolbar>

  <v-sheet elevation="0" class="mx-auto landing-warpper" rounded>
    <v-sheet max-width="1600" class="mx-auto pa-5" elevation="0">
      <v-row align="center">
        <v-col cols="12" md="4" v-for="item in articles" :key="item.id">
          <v-card max-width="400" class="mx-auto">
            <v-img cover :src="item.image" height="200px"></v-img>
            <v-card-title class="text-h6 font-weight-bold">
              {{ item.title }}
            </v-card-title>
            <v-card-subtitle>
              Last Read At{{ item.lastReadAt }}</v-card-subtitle
            >
            <v-card-text>
              {{ item.content }}
            </v-card-text>
            <v-card-actions>
              <v-btn text color="primary">
                <v-icon class="mr-2">mdi-account</v-icon>
                {{ item.author }}
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn text color="primary">
                <v-icon class="mr-2">mdi-calendar</v-icon>
                {{ item.createAt }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
