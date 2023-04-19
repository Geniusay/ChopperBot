<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Toolbar1 from "./components/Toolbar1.vue";
import Toolbar2 from "./components/Toolbar2.vue";
import Toolbar3 from "./components/Toolbar3.vue";
import Toolbar4 from "./components/Toolbar4.vue";
import Toolbar5 from "./components/Toolbar5.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Toolbar
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
  <Toolbar1 />
  <Toolbar2 />
  <Toolbar3 />
  <Toolbar4 />
  <Toolbar5 />
</template>

<style lang="scss"></style>
