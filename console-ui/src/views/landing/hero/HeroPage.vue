<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Hero1 from "./components/Hero1.vue";
import Hero2 from "./components/Hero2.vue";
import Hero3 from "./components/Hero3.vue";
import Hero4 from "./components/Hero4.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Hero
        <v-chip size="small" class="ma-2"> 4 Components </v-chip>
      </h3>
    </div>
    <v-spacer></v-spacer>
    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>
  </v-toolbar>
  <Hero1 />
  <Hero2 />
  <Hero3 />
  <Hero4 />
</template>

<style lang="scss"></style>
