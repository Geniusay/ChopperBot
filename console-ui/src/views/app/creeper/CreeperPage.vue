<script setup lang="ts">
import {allCreeper} from "@/api/creeperApi";
import {useCreeperStore} from "@/views/app/creeper/creeperStore";
import CreeperCard from "@/views/app/creeper/component/CreeperCard.vue"
const creeperStore = useCreeperStore();

onMounted(async()=>{
  await allCreeper().then(res=>{
    creeperStore.creeperList = res.data['list']
  })
})

</script>

<template>
  <div class="app-container">
    <CreeperCard :creepers="creeperStore.creeperList"></CreeperCard>
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
