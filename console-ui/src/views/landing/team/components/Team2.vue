<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import members from "@/data/members";
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Team2</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <h1 class="text-h3 font-weight-bold text-center my-10">Meet the Team</h1>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="6" md="4" lg="3" v-for="member in members">
          <v-card
            max-width="300"
            elevation="0"
            class="text-center mx-auto pa-5 pa-md-10 d-flex flex-column justify-center"
          >
            <v-avatar class="mx-auto" size="200">
              <v-img :src="member.avatar" alt="alt" />
            </v-avatar>
            <h6 class="text-h6">{{ member.name }}</h6>
            <p class="text-body-1 text-blue-grey">{{ member.position }}</p>
            <p class="text-body-2 text-grey">{{ member.description }}</p>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
