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
      <span>Toobar4</span>
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
      <v-btn icon>
        <v-badge dot color="success">
          <v-icon>mdi-google</v-icon>
        </v-badge>
      </v-btn>
      <v-btn icon>
        <v-badge dot color="success">
          <v-icon>mdi-home-outline</v-icon>
        </v-badge>
      </v-btn>
      <v-btn icon>
        <v-badge dot color="success">
          <v-icon>mdi-home-outline</v-icon>
        </v-badge>
      </v-btn>
      <v-spacer></v-spacer>

      <v-btn class="text-none" stacked>
        <v-badge dot color="success">
          <v-icon>mdi-home-outline</v-icon>
        </v-badge>
      </v-btn>

      <v-btn class="text-none" stacked>
        <v-icon>mdi-account-multiple-outline</v-icon>
      </v-btn>

      <v-btn class="text-none" stacked>
        <v-badge content="9+" color="error">
          <v-icon>mdi-store-outline</v-icon>
        </v-badge>
      </v-btn>

      <v-btn class="text-none" stacked>
        <v-badge content="2" color="error">
          <v-icon>mdi-bell-outline</v-icon>
        </v-badge>
      </v-btn>

      <v-avatar class="mx-5">
        <v-img src="https://cdn.vuetifyjs.com/images/lists/1.jpg"></v-img>
      </v-avatar>
    </v-toolbar>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
