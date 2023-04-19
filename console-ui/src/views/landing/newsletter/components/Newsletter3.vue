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
      <span>Newsletter3</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper text-left"
    color="#F2F5F8"
    rounded
  >
    <v-row align="center">
      <v-col cols="12" md="6">
        <v-sheet color="transparent" elevation="0" class="py-10">
          <v-card
            color="transparent"
            elevation="0"
            max-width="800"
            class="mx-auto my-10 px-10"
          >
            <h1 style="color: #4a4d6d" class="font-weight-black text-h3">
              Get the best insights directly to your
              <span class="text-primary">inbox</span>
            </h1>
            <h2 class="text-body-1 text-secondary mt-4 mx-auto">
              Stack is a Spatial Browser for Mindful Online Living
            </h2>
            <v-text-field
              class="bg-white mt-5"
              color="primary"
              hide-details
              variant="outlined"
              placeholder="email"
            ></v-text-field>
            <v-checkbox
              color="primary"
              label="I have read and agree to the terms"
            >
            </v-checkbox>
            <v-btn
              block
              height="60"
              color="primary"
              class="font-weight-bold mb-5"
              >Subscribe to the Newsletter</v-btn
            >
          </v-card>
        </v-sheet>
      </v-col>
      <v-col md="6">
        <v-card class="h-full" color="blue">
          <v-img
            cover
            src="https://images.unsplash.com/photo-1666875753105-c63a6f3bdc86?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80"
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
