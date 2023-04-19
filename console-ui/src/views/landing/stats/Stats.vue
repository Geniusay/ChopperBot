<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Stats1 from "./components/Stats1.vue";
import Stats2 from "./components/Stats2.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Stats
        <v-chip size="small" class="ma-2"> 2 Components </v-chip>
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
  <div>
    <Stats1 />
    <Stats2 />
  </div>
</template>

<style lang="scss"></style>
