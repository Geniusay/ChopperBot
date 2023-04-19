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
      <span>Newsletter1</span>
    </v-toolbar-title>
  </v-toolbar>

  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper"
    color="#F2F5F8"
    rounded
  >
    <v-sheet
      color="transparent"
      elevation="0"
      max-width="1600"
      class="mx-auto pa-5"
    >
      <v-container class="">
        <v-row align="center" justify="center">
          <v-col cols="12" lg="3">
            <h1 class="text-h5 font-weight-bold text-center">
              Receive updates in your inbox
            </h1>
          </v-col>
          <v-col cols="12" sm="9" md="7">
            <v-text-field
              class="bg-white"
              color="primary"
              hide-details
              variant="outlined"
              placeholder="email"
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3" md="2">
            <v-btn size="x-large" block color="primary font-weight-bold"
              >Subscribe</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
