<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useAppStore } from "@/stores/appStore";
import Loading from "@/components/loading/Loading02.vue";
const appStore = useAppStore();
</script>

<template>
  <v-card
    v-if="appStore.globalLoading"
    color="white"
    class="global-loading d-flex align-center justify-center"
    height="100vh"
  >
    <Loading />
  </v-card>
</template>

<style scoped lang="scss">
.main-bg {
  min-height: calc(100vh - 64px);
}
</style>
