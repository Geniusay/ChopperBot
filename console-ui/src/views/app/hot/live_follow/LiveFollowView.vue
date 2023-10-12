<script setup lang="ts">
import LiveFollowCard from "@/views/app/hot/live_follow/pages/LiveFollowPage.vue";
</script>

<template>
  <div class="app-container">
    <router-view v-slot="{ Component }">
      <transition name="fade">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<style scoped lang="scss">
.app-container {
  height: calc(100vh - 240px);
  width: 100%;
  font-size: 13px;
  padding: 20px;
}
</style>
