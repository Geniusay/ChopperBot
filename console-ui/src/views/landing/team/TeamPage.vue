<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Team1 from "./components/Team1.vue";
import Team2 from "./components/Team2.vue";
import Team3 from "./components/Team3.vue";
import Team4 from "./components/Team4.vue";
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Top Toolbar -->
  <!-- ---------------------------------------------- -->
  <v-toolbar height="100" color="primary">
    <div class="ml-5">
      <h3 class="text-h5 font-weight-bold">
        Team
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
  <div>
    <Team1 />
    <Team2 />
    <Team3 />
    <Team4 />
  </div>
</template>

<style lang="scss"></style>
