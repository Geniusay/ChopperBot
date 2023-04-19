<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Newsletter1 from "./components/Newsletter1.vue";
import Newsletter2 from "./components/Newsletter2.vue";
import Newsletter3 from "./components/Newsletter3.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Newsletter
        <v-chip size="small" class="ma-2"> 3 Components </v-chip>
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
  <Newsletter1 />
  <Newsletter2 />
  <Newsletter3 />
</template>

<style lang="scss"></style>
