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
      <span>Team4</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <h1 class="text-h3 font-weight-bold text-center text-blue-grey my-10">
        Meet the Team
      </h1>
      <v-row align="center" justify="center">
        <v-col cols="12" md="6" v-for="member in members">
          <v-card class="text-left mx-auto d-flex">
            <div style="width: 200px">
              <v-img height="100%" cover :src="member.avatar" alt="alt" />
            </div>

            <div class="info flex-1 pa-10">
              <h6 class="text-h6">{{ member.name }}</h6>
              <p class="text-body-1 text-blue-grey">{{ member.position }}</p>
              <p class="text-body-2 text-grey">{{ member.description }}</p>
              <div>
                <v-btn
                  density="compact"
                  icon
                  class="mr-2 mt-3"
                  v-for="item in member.social"
                  :to="item.link"
                  ><v-icon class="text-blue-grey">{{
                    item.icon
                  }}</v-icon></v-btn
                >
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
