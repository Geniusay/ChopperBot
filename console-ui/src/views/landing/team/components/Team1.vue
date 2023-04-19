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
      <span>Team1</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-item-group mandatory selected-class="active-card">
        <v-row align="center" justify="center">
          <v-col cols="12" sm="6" md="4" lg="3" v-for="member in members">
            <v-item v-slot="{ isSelected, selectedClass, toggle }">
              <v-card
                :theme="isSelected ? 'dark' : 'light'"
                max-width="300"
                class="base-card text-center mx-auto pa-10 pa-md-15 d-flex flex-column justify-center"
                :class="selectedClass"
                @click="toggle"
              >
                <v-avatar class="mx-auto" size="200">
                  <v-img :src="member.avatar" alt="alt" />
                </v-avatar>
                <h6 class="text-h6">{{ member.name }}</h6>
                <p class="text-body-1 text-blue-grey">{{ member.position }}</p>
                <p class="text-body-2 text-grey">{{ member.description }}</p>
              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-item-group>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.base-card {
  transition: 0.3s ease-out;
}

.active-card {
  border: 1px solid #e5e5e5;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.05);
  transform: scale(1.05);
  transition: 0.3s ease-out;
}
</style>
