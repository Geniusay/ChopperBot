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
      <span>Hero2</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper"
    color="#F2F5F8"
    rounded
  >
    <v-container class="text-center">
      <v-row>
        <v-col cols="12" md="6">
          <v-sheet color="transparent" elevation="0">
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
          <v-card>
            <v-img
              height="420"
              src="https://img.freepik.com/free-vector/group-people-working-together_52683-28615.jpg?w=2000&t=st=1678176451~exp=1678177051~hmac=5848d02937d139b407d25a00e631d57a3161339e32e353f832f53e31d6424624"
            ></v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
