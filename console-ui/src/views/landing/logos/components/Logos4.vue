<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import logos from "@/data/logos";
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Logo1</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-row align="center" justify="center">
        <v-col cols="6" sm="6" md="4" lg="3" v-for="logo in logos">
          <v-card
            elevation="0"
            class="pa-5 base-card text-center mx-auto d-flex flex-column justify-center"
          >
            <v-img height="60" :src="logo.logoUrl"></v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
