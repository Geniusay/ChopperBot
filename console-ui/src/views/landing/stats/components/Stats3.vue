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
      <span>Pricing3</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper text-center"
    color="#F2F5F8"
    rounded
  >
    <v-row align="center">
      <v-col cols="12" md="6">
        <v-sheet color="transparent" elevation="0" class="py-10">
          <img class="mx-auto" width="200" src="@/assets/logo2.svg" alt="" />
          <v-card
            color="transparent"
            elevation="0"
            max-width="800"
            class="mx-auto my-10"
          >
            <h1
              style="color: #4a4d6d"
              class="font-weight-black text-h3 text-lg-h2 text-xl-h1"
            >
              We organize chaotic
              <span class="text-primary">internet</span>
            </h1>
            <h2 class="text-h6 text-secondary mt-4 mx-auto">
              Stack is a Spatial Browser for Mindful Online Living
            </h2>
          </v-card>
          <div>
            <v-btn
              width="200"
              height="60"
              class="text-white mr-5"
              color="primary"
              >Get Stack</v-btn
            >
            <v-btn
              variant="outlined"
              width="200"
              height="60"
              class="text-primary"
              >Watch Demo</v-btn
            >
          </div>
        </v-sheet>
      </v-col>
      <v-col cols="0" md="6">
        <v-card class="h-full">
          <v-img
            cover
            height="100%"
            src="https://images.unsplash.com/photo-1666875753105-c63a6f3bdc86?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80"
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
