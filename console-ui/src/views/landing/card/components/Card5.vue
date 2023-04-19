<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card5</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-item-group selected-class="active-card">
        <v-row align="center" justify="center">
          <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 3">
            <v-item v-slot="{ isSelected, selectedClass, toggle }">
              <v-card
                :theme="isSelected ? 'dark' : 'light'"
                elevation="0"
                height="400"
                width="300"
                class="mx-auto pa-10 pa-md-15 d-flex flex-column justify-center card"
                :class="selectedClass"
                @click="toggle"
              >
                <h1 class="text-white">Let Test Some thing{{ i }}</h1>
              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-item-group>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.landing-warpper {
  background-image: linear-gradient(135deg, #ce9ffc, #7367f0);
}

.card {
  background-color: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-radius: 1rem;
  box-shadow: rgba(17, 12, 46, 0.15) 0px 48px 100px 0px;
}

.active-card {
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.05);
}
</style>
