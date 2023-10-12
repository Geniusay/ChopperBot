<script setup lang="ts">
import {followList} from "@/api/hot/liveFollowApi";
import {useLiveFollowStore} from "@/views/app/hot/live_follow/liveFollowStore";
import LiveFollowCard from "@/views/app/hot/live_follow/component/LiveFollowCard.vue";

const liveFollowStore = useLiveFollowStore();
onMounted(async()=>{
  await followList().then(res=>{
    liveFollowStore.liveFollowList = res.data['list']
  })
})

</script>

<template>
  <div class="app-container">
    <LiveFollowCard :liveFollows="liveFollowStore.liveFollowList"></LiveFollowCard>
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
