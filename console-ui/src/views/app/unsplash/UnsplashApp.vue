<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: Imitate Nitori App
-->
<script setup lang="ts"></script>

<template>
  <div>
    <!-- <div>
      <v-btn color="success" to="/apps/unsplash/">photos</v-btn>
      <v-btn color="success" to="/apps/unsplash/user">user</v-btn>
    </div> -->
    <router-view v-slot="{ Component }" class="gp">
      <transition name="fade">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<style scoped lang="scss"></style>
