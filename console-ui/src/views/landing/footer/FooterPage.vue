<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Footer1 from "./components/Footer1.vue";
import Footer2 from "./components/Footer2.vue";
import Footer3 from "./components/Footer3.vue";
import Footer4 from "./components/Footer4.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Footer
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
  <div class="pa-5">
    <Footer1 />
    <Footer2 />
    <Footer3 />
    <Footer4 />
  </div>
</template>

<style lang="scss"></style>
