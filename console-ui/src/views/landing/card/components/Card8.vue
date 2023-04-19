<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card8</span>
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
        <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 3">
          <v-card
            class="card-2 mx-auto my-5 pa-2"
            height="400"
            width="300"
            variant="flat"
          >
            <v-card height="100%" elevation="0"> {{ i }}</v-card>
          </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.landing-warpper {
  background-image: linear-gradient(
    318deg,
    rgba(62, 67, 83, 1) 0%,
    rgba(75, 80, 108, 1) 50%,
    rgba(80, 87, 125, 1) 100%
  );
}

.card {
  position: relative;
  height: 400px;
  width: 300px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
}

.card-2 {
  background-image: linear-gradient(
    318deg,
    rgba(148, 76, 206, 1) 0%,
    rgba(191, 60, 179, 1) 50%,
    rgba(236, 39, 145, 1) 100%
  );
}
</style>
