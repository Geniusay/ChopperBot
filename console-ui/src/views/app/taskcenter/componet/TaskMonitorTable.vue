<script setup lang="ts">
import {useTaskStore} from "@/views/app/taskcenter/taskStore";
import VideoDownloadMonitor from "@/components/monitor/VideoDownloadMonitor.vue";
import {allMonitors, allTask} from "@/api/creeperApi";
import {Monitor} from "@/views/app/taskcenter/monitorTypes";
import BarrageDownloadMonitor from "@/components/monitor/BarrageDownloadMonitor.vue";

const taskStore = useTaskStore()

onMounted(async ()=>{
  await allMonitors().then(res=>{
    for(var i=0;i<res.data["list"].length;i++){
      const monitor = res.data["list"][i] as Monitor
      taskStore.monitors.set(monitor.taskId,monitor)
    }
  })
})

let monitors = taskStore.monitors
const themeDrawer = ref(false);


</script>

<template>
  <div>
    <div class="drawer-button" @click="themeDrawer = true">

      <v-icon :class="monitors.size!==0?'text-white work':'text-white'">mdi-cog-outline</v-icon>
    </div>

    <v-navigation-drawer
      v-model="themeDrawer"
      location="right"
      temporary
      width="600"
      class="theme-drawer pa-8"
    >
      <div class="top-area">
        <div class="d-flex align-center">
          <b>Task Monitor</b>
          <v-spacer></v-spacer>
          <v-btn
            variant="text"
            size="small"
            rounded
            icon="mdi-close"
            @click="themeDrawer = false"
          >
          </v-btn>
        </div>
        <div>See Running Task.</div>
      </div>
      <div  v-for="(value, key) in monitors.entries()" :key="key">
        <hr class="my-6" />
        <VideoDownloadMonitor v-if="value[1].monitorType==='live'" :task-id="value[1].taskId"></VideoDownloadMonitor>
        <BarrageDownloadMonitor v-else :task-id="value[1].taskId"></BarrageDownloadMonitor>
      </div>
    </v-navigation-drawer>
  </div>
</template>

<style lang="scss" scoped>

.drawer-button {
  position: fixed;
  background-color: #705cf6;
  top: 400px;
  right: 0px;
  z-index: 999;
  padding: 0.5rem 1rem;
  border-top-left-radius: 0.5rem;
  border-bottom-left-radius: 0.5rem;
  box-shadow: 1px 1px 9px #705cf6;
  transition: all 0.5s;
  cursor: pointer;
  &:hover {
    box-shadow: 1px 1px 18px #705cf6;
    transition: all 0.5s;
  }

  .v-icon {
    font-size: 1.3rem;
    animation: linear infinite;
  }

  .work {
    font-size: 1.3rem;
    animation: rotation 1s linear infinite;
  }

  @keyframes rotation {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }

}

hr {
  background-image: linear-gradient(
      90deg,
      transparent,
      rgba(0, 0, 0, 0.4),
      transparent
  ) !important;
  background-color: transparent;
  opacity: 0.25;
  border: none;
  height: 1px;
}
</style>
