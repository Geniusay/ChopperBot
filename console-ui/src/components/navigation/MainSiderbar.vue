<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import configs from "@/configs";
import MainMenu from "@/components/navigation/MainMenu.vue";
import { useCustomizeThemeStore } from "@/stores/customizeTheme";
import { Icon } from "@iconify/vue";
const customizeTheme = useCustomizeThemeStore();
const navigation = ref(configs.navigation);

const openGithubSite = () => {
  window.open("https://github.com/Geniusay/ChopperBot", "_blank");
};
</script>

<template>
  <v-navigation-drawer
    v-model="customizeTheme.mainSidebar"
    elevation="1"
    id="mainMenu"
  >
    <!-- ---------------------------------------------- -->
    <!---Top Area -->
    <!-- ---------------------------------------------- -->
    <template v-if="!customizeTheme.miniSidebar" v-slot:prepend>
      <v-card
        elevation="0"
        height="100"
        class="d-flex align-center justify-center"
      >
        <h1 v-if="customizeTheme.darkTheme" style="background-image:-webkit-linear-gradient(left,#4f4f4f,#a1a1a1,#c9c9c9);-webkit-background-clip:text;-webkit-text-fill-color:transparent;font-family: 华文琥珀;font-size: 35px;margin-bottom: 10px">ChopperBot</h1>
        <h1 v-else="customizeTheme.darkTheme" style="background-image:-webkit-linear-gradient(left,#4f4f4f,#a1a1a1,#c9c9c9);-webkit-background-clip:text;-webkit-text-fill-color:transparent;font-family: 华文琥珀;font-size: 35px;margin-bottom: 10px">ChopperBot</h1>
<!--        <img-->
<!--          v-if="customizeTheme.darkTheme"-->
<!--          width="200"-->
<!--          src="@/assets/logo_dark.svg"-->
<!--          alt=""-->
<!--        />-->
<!--        <img-->
<!--          v-else="customizeTheme.darkTheme"-->
<!--          width="200"-->
<!--          src="@/assets/logo_light.svg"-->
<!--          alt=""-->
<!--        />-->
      </v-card>
    </template>

    <!-- ---------------------------------------------- -->
    <!---Nav List -->
    <!-- ---------------------------------------------- -->
    <perfect-scrollbar class="scrollnav">
      <main-menu :menu="navigation.menu"></main-menu>
    </perfect-scrollbar>
    <!-- ---------------------------------------------- -->
    <!---Bottom Area -->
    <!-- ---------------------------------------------- -->
    <template v-if="!customizeTheme.miniSidebar" v-slot:append>
      <v-card theme="dark" height="225" class="pa-3" style="margin-bottom: 100px" variant="text">
        <v-card
          class="d-flex flex-column gradient pa-2"
          :class="customizeTheme.primaryColor.colorName"
          height="200"
        >
          <v-card-title>
            <v-btn
              class="mr-2"
              size="40"
              color="white"
              :class="`text-${customizeTheme.primaryColor.colorName}`"
              icon
            >
              <Icon width="30" icon="line-md:github-loop" />
            </v-btn>
            <a target="_blank" href="https://github.com/Time-Machine-Lab">TML</a>
          </v-card-title>
          <v-card-subtitle> </v-card-subtitle>
          <v-card-text>
            <div><b>Github:</b></div>
            <div><a target="_blank" href="https://github.com/Geniusay/ChopperBot">github.com/ChopperBot</a></div>
          </v-card-text>
          <v-card-actions>
            <v-btn
              color="white"
              block
              prepend-icon="mdi-thumb-up-outline"
              variant="elevated"
              @click="openGithubSite"
            >
              Star-Me
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-card>
    </template>
  </v-navigation-drawer>
</template>

<style scoped lang="scss"></style>
