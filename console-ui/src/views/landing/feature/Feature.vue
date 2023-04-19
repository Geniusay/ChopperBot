<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Feature1 from "./components/Feature1.vue";
import Feature2 from "./components/Feature2.vue";
import Feature3 from "./components/Feature3.vue";
import Feature4 from "./components/Feature4.vue";
import Feature5 from "./components/Feature5.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Feature
        <v-chip size="small" class="ma-2"> 5 Components </v-chip>
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
    <Feature1 />
    <Feature2 />
    <Feature3 />
    <Feature4 />
    <Feature5 />
  </div>
</template>

<style lang="scss"></style>
