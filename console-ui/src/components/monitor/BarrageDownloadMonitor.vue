<script lang="ts" setup>
import moment from "moment";
import { useTheme } from "vuetify";
import WebSocketClient from "@/utils/ws/webSocket";
import {useTaskStore} from "@/views/app/taskcenter/taskStore";
const taskStore = useTaskStore()


const props = defineProps({
  taskId: {
    type: String,
    default:""
  },
});
const pre = ref(0)
const total = ref(0);
const useTime = ref("0 s")
let preBarrage = 0
const webSocket = new WebSocketClient(()=>{
  webSocket.sendMsg(webSocket.encodeMsg("monitor",props.taskId))
},(event)=>{
  let dataMap = webSocket.decodeMsg(event.data)
  if(dataMap.get("data")==="close"){
    taskStore.monitors.delete(props.taskId)
  }
  let data = JSON.parse(dataMap.get("data"))
  let type = dataMap.get("type")
  if(type=="monitor"){
    if(data["taskId"]==props.taskId){
      total.value = Number(data["total"])
      pre.value = total.value - preBarrage
      useTime.value = data["useTime"]
    }
    preBarrage = total.value
  }
});

const loading = ref(true);

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>

<template>
  <v-sheet :elevation="24">
    <v-card
      loading
      :title="$t('monitor.barrageMonitor')"
      :subtitle="taskId"
    >
      <v-img
        class="align-end text-white"
        height="200"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        cover
      ></v-img>
      <v-table>
        <thead>
        <tr>
          <th class="text-left">
            Pre Barrage(barrage/s)
          </th>
          <th class="text-left">
            Total Barrage(barrage)
          </th>
          <th  class="text-left">
            Running Time
          </th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>{{ pre }}</td>
          <td>{{ total }}</td>
          <td>{{ useTime }}</td>
        </tr>
        </tbody>
      </v-table>
      <v-card-actions>
        <v-btn>Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-sheet>
</template>

