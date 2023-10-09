<template>
  <v-table class="mt-5">
    <thead>
    <tr>
      <th class="text-subtitle-1 font-weight-semibold">监控守卫名称</th>
      <th class="text-subtitle-1 font-weight-semibold">轮询时间</th>
      <th class="text-subtitle-1 font-weight-semibold">失败重试次数</th>
      <th class="text-subtitle-1 font-weight-semibold">上次更新</th>
    </tr>
    </thead>
    <tbody class="text-body-1">
    <tr v-for="(item,index) in hotGuardStore.guards" :key="item.id">
      <td class="font-weight-bold">
        <div class="d-flex align-center py-1">
          <div class="ml-5">
            <p class="font-weight-bold">{{ item.guardName }}</p>
          </div>
        </div>
      </td>
      <td>
        <div class="d-flex align-center py-1">
          <div class="ml-5">
            <p class="font-weight-bold">{{ nanosToHMS_CN(item.delayTime) }}</p>
          </div>
        </div>
      </td>
      <td>
        <div class="d-flex align-center py-1">
          <div class="ml-5">
            <p class="font-weight-bold">{{ item.failRetryTimes }}</p>
          </div>
        </div>
      </td>
      <td>
        <div class="d-flex align-center py-1">
          <div class="ml-5">
            <p class="font-weight-bold">{{isoStrToNormal(item.preGuardTime)}}</p>
          </div>
        </div>
      </td>
    </tr>
    </tbody>
  </v-table>
</template>

<script setup lang="ts">
import {getGuards, getHotGuardSetting} from "@/api/hot/hotGuardApi";
import {useHotGuardStore} from "@/views/app/hot/hot_guard/hotGuardStore";
import {nanosToHMS_CN,isoStrToNormal} from "@/utils/timeUtils";

const hotGuardStore = useHotGuardStore()

onMounted(async()=>{
  await getGuards().then(res=>{
    hotGuardStore.guards = res.data['list']
  })
})


</script>

<style scoped>

</style>
