<script setup lang="ts">
import {Task} from "../taskTypes"
import {useTaskStore} from "@/views/app/taskcenter/taskStore";
import CopyLabel from "@/components/common/CopyLabel.vue";
import WebSocketClient from "@/utils/ws/webSocket";
import {useSnackbarStore} from "@/stores/snackbarStore";
import {allTask} from "@/api/creeperApi";

const snackbarStore = useSnackbarStore();

const taskStore = useTaskStore();

const props = defineProps<{
  tasks: Task[];
}>();



const searchKey = ref("");
const avatar = "/src/assets/images/img/Creeper.png"
const snackbar = ref(false)

const getLabelColor = (id: string) => {
  // Find the label by id from the labels array
  const label = taskStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.color : "";
}

const getLabelIcon = (id: string) => {
  // Find the label by id from the labels array
  const label = taskStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.icon : "";
};

const searchPlugin = computed(()=>{
  return props.tasks.filter((plugin)=>{
    return plugin.taskId.includes(searchKey.value.toLowerCase())
  })
})

const stopTask = (task: Task) => {
  if(task.status!="running"){
     snackbar.value = true
     return
  }
  taskStore.stopTask(task)
}

const closeSnackbar = () =>{
  snackbar.value=false
}

const startMonitor = (task: Task) =>{
  if(task.hasMonitor===0){
    taskStore.startMonitor(task)
  }else{
    snackbarStore.showErrorMessage("该任务已启动监控器")
  }
}

const stopMonitor = (task: Task) =>{
  if(task.hasMonitor===1){
    taskStore.stopTask(task)
  }else{
    snackbarStore.showErrorMessage("该任务已暂停监控器")
  }
}

const headers = [
  { title: "ID", key: "taskId" },
  { title: "爬虫", key: "avatar" },
  { title: "开始时间", key: "startTime" },
  { title: "结束时间", key: "endTime" },
  { title: "运行状态", key: "status", align: "center" },
  { title: "监视器", key: "hasMonitor" },
  { title: "暂停", key: "pause" },
];
</script>


<template>
  <v-card height="100%">
    <!-- ---------------------------------------------- -->
    <!-- Filter Input -->
    <!-- ---------------------------------------------- -->
    <v-text-field
      clearable
      variant="solo"
      class="elevation-1 ma-3"
      hide-details
      prepend-inner-icon="mdi-magnify"
      placeholder="输入任务ID"
      v-model="searchKey"
    ></v-text-field>
    <!-- ---------------------------------------------- -->
    <!-- List -->
    <!-- ---------------------------------------------- -->
    <v-card-text class="table-container">
      <v-data-table
        :headers="headers"
        :items="tasks"
        :search="searchKey"
        :loading="taskStore.loading"
        :items-length="tasks.length"
        item-value="id"
        fixed-header
        height="900"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td class="font-weight-bold">
              <CopyLabel :text="item.columns.taskId" />
            </td>
            <td>
              <v-avatar size="30">
                <img :src="avatar" alt="alt" />
              </v-avatar>
            </td>
            <td>{{ item.columns.startTime=="nil"?"未开始":item.columns.startTime }}</td>

            <td>{{ item.columns.endTime=="nil"?"未结束":item.columns.endTime }}</td>

            <td class="text-center">
              <v-chip
                size="small"
                :color="getLabelColor(item.columns.status)"
                class="font-weight-bold"
              >
                <v-icon
                  start
                  :icon="getLabelIcon(item.columns.status)"
                ></v-icon>
                {{
                  item.columns.status
                }}</v-chip
              >
            </td>
            <td>
              <v-btn @click="startMonitor(item.columns)">
                  <v-icon v-if="item.columns.hasMonitor===1" color="success">mdi mdi-monitor</v-icon>
                  <v-icon v-else color="warning">mdi mdi-monitor</v-icon>
              </v-btn>
            </td>
            <td>
              <v-btn size="x-small" rounded="xl" elevation="8" prepend-icon="mdi mdi-pause" @click="stopTask(item.columns)">
                Pause
              </v-btn>
              <v-snackbar
                v-model="snackbar"
                location="center"
              >
                无法暂停，因为改爬虫并没有运行!

                <template v-slot:actions>
                  <v-btn @click="closeSnackbar">Close</v-btn>
                </template>
              </v-snackbar>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-card-text>
  </v-card>
</template>

