<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Testimonial1 from "./components/Testimonial1.vue";
import Testimonial2 from "./components/Testimonial2.vue";
import Testimonial3 from "./components/Testimonial3.vue";
import Testimonial4 from "./components/Testimonial4.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <v-toolbar-title class="text-h5 font-weight-bold">
      <!-- <v-icon class="mr-2">mdi-account</v-icon> -->
      <span>Testimonial</span>
      <v-chip class="ma-2" color="primary" text-color="white">
        4 Components
      </v-chip>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>
  </v-toolbar>
  <Testimonial1 />
  <Testimonial2 />
  <Testimonial3 />
  <Testimonial4 />
</template>

<style lang="scss"></style>
