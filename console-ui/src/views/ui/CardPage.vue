<template>
  <v-container>
    <Icon class="animate-icon" icon="logos:bamboo" />
    <v-card class="card-container d-flex pa-5">
      <v-container>
        <h1 class="text-h4">Card 1</h1>
        <v-row align="center" justify="center">
          <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 4" :key="i">
            <v-card class="card-1 pa-5 mx-auto" height="400" width="300">
              <h1 class="text-white">Let Test Some thing</h1>

              <v-img src="https://picsum.photos/350/165?random"></v-img>
            </v-card>
          </v-col>
        </v-row>

        <v-divider class="my-10"></v-divider>
        <h1 class="text-h4">Card 2</h1>
        <v-row align="center" justify="center">
          <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 4" :key="i">
            <div class="card-2">
              <div class="cup-warp">
                <Icon class="animate-icon cup" icon="fxemoji:sunrays" />

                <!-- <Icon class="animate-icon" icon="logos:bamboo" /> -->
              </div>
              <div class="button">GET INVETED</div>
            </div>
          </v-col>
        </v-row>
        <v-divider class="my-10"></v-divider>
        <h1 class="text-h4">Card 3</h1>
        <v-row align="center" justify="center">
          <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 4" :key="i">
            <div class="card-3 mx-auto my-5">
              <v-card height="400" width="300"> </v-card>
            </div>
          </v-col>
        </v-row>
        <v-divider class="my-10"></v-divider>
        <h1 class="text-h4">Card 4</h1>
        <v-row align="center" justify="center">
          <v-col cols="12" md="6" lg="4" xl="3" v-for="i in 4" :key="i">
            <div class="card-4 mx-auto my-5">
              <v-card variant="flat" height="400" width="300">
                <v-img
                  :src="'https://picsum.photos/400/400?random=' + i"
                ></v-img>
              </v-card>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-container>
</template>
<script setup lang="ts">
import { Icon } from "@iconify/vue";
</script>
<style scoped lang="scss">
$white: #ffffff;
$black: #000000;

.card-container {
  // background: linear-gradient(135deg, #56ccf2, #2f80ed);
  background: linear-gradient(
    135deg,
    rgb(232, 245, 246),
    rgb(234, 207, 226),
    rgb(205, 218, 240)
  );
  background-size: cover;
  background-attachment: fixed;
}
.card-1 {
  background-color: rgba(255, 255, 255, 0.1);
  border: 1px solid transparentize($white, 0.5);
  border-radius: 1rem;
  box-shadow: rgba(17, 12, 46, 0.15) 0px 48px 100px 0px;
}

.card-2 {
  position: relative;
  height: 440px;
  width: 300px;
  margin: 70px auto 0px;
  padding: 50px 30px;
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

  .cup-warp {
    width: 100px;
    height: 100px;
    background-color: #fff;
    position: absolute;
    left: 50%;
    top: 0;
    transform: translate(-50%, -50%);
    border-radius: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .cup {
    width: 70px;
    height: 70px;
    color: #ffda00;
    filter: drop-shadow(0 0 40px rgba(255, 218, 0, 0.7));
    animation: rotate 10s linear infinite;
  }

  @keyframes rotate {
    0% {
      transform: rotate(0deg);
    }

    100% {
      transform: rotate(360deg);
    }
  }
}

.card-3 {
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

.card-4 {
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
}
</style>
