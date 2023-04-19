<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card7</span>
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
          <div class="card mx-auto my-5">
            <v-card height="400" width="300"> {{ i }}</v-card>
          </div>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.landing-warpper {
  background-image: linear-gradient(135deg, #ce9ffc, #7367f0);
}

.card {
  position: relative;
  height: 400px;
  width: 300px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
  &:after {
    position: absolute;
    left: 5px;
    bottom: -7px;
    width: 96%;
    height: 7px;
    background-color: #e2eaf1;
    border-radius: 0 0 5px 5px;
    content: "";
  }
  &:before {
    position: absolute;
    left: 10px;
    bottom: -14px;
    width: 92%;
    height: 7px;
    background-color: #cfd5e3;
    border-radius: 0 0 5px 5px;
    content: "";
    box-shadow: 0 5px 60px rgba(0, 0, 0, 0.5);
  }
}
</style>
