<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import { Icon } from "@iconify/vue";
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card6</span>
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
          <div class="card">
            <div class="cup-warp">
              <Icon class="animate-icon cup" icon="fxemoji:sunrays" />

              <!-- <Icon class="animate-icon" icon="logos:bamboo" /> -->
            </div>
            <div class="button">GET INVETED{{ i }}</div>
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
</style>
