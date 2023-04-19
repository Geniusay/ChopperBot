<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <!-- <v-icon class="mr-2">mdi-account</v-icon> -->
      <span>Toobar5</span>
    </v-toolbar-title>
  </v-toolbar>

  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper"
    color="#F2F5F8"
    rounded
  >
    <v-toolbar color="primary">
      <div class="ml-5 mr-5">
        <h3 class="text-h5 font-weight-bold">Vuetify</h3>
      </div>
      <v-btn active class="text-none" stacked> Home </v-btn>
      <v-btn class="text-none" stacked> Products </v-btn>
      <v-btn class="text-none" stacked> Services </v-btn>
      <v-btn class="text-none" stacked> Contact </v-btn>
      <v-btn class="text-none" stacked> About </v-btn>
      <v-spacer></v-spacer>

      <v-btn class="text-none"> En </v-btn>
    </v-toolbar>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
