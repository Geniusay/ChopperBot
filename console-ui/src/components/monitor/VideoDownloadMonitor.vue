<script lang="ts" setup>
import moment from "moment";
import { useTheme } from "vuetify";
import { formatCurrency } from "@/utils/formatCurrency";
import PercentTrend from "@/components/common/PercentTrend.vue";
import WebSocketClient from "@/utils/ws/webSocket";
import {useTaskStore} from "@/views/app/taskcenter/taskStore";
const taskStore = useTaskStore()
const formatDate = (date: string) => {
  return date ? moment(date).format("D MMM") : "";
};

let props = defineProps({
  taskId: {
    type: String,
    default:"",
  },
  value: {
    type: Number,
    default: 0,
  },
  percentage: {
    type: Number,
    default: 0,
  },
  series: {
    type: Array,
    default: () => [
      {
        name: "speed",
        data: [100.12, 32, 45, 13,100],
      },
    ],
  },
  xaxis: {
    type: Object,
    default: () => ({
      type: "category",
      categories: [
        "2018-09-19T00:00:00.000Z",
        "2018-09-20T00:00:00.000Z",
        "2018-09-22T00:00:00.000Z",
        "2018-09-23T00:00:00.000Z",
        "2018-09-25T00:00:00.000Z"
      ],
      // tickAmount: 3
    }),
  },
  label: {
    type: String,
    default: "Video Monitor",
  },
  actionLabel: {
    type: String,
    default: "View Report",
  },
  options: {
    type: Object,
    default: () => ({}),
  },
});
const total = ref("0.00 B")
const avg = ref("0.00 B");
const now = ref("0.00 B")
const useTime = ref("0 s")
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
        avg.value = data["avg"]
        total.value = data["total"]
        now.value = data["now"]
        useTime.value = data["useTime"]
      }
    }
});

const { themes, current } = useTheme();
const chartOptions = computed(() => {
  const primaryColor = current.value.dark
    ? themes.value["dark"].colors.primary
    : themes.value["light"].colors.primary;

  return {
    chart: {
      height: 120,
      type: "area",
      sparkline: {
        enabled: true,
      },
      animations: {
        speed: 400,
      },
    },
    series: props.series,
    colors: [primaryColor],
    fill: {
      type: "solid",
      colors: [primaryColor],
      opacity: 0.15,
    },
    stroke: {
      curve: "smooth",
      width: 2,
    },
    xaxis: props.xaxis,
    tooltip: {
      followCursor: true,
      theme: "dark",
      custom: function ({ ctx, series, seriesIndex, dataPointIndex, w }: any) {
        const seriesName = "speed";
        return `<div class="rounded-lg pa-1 text-caption">
                <div>${series[seriesIndex][dataPointIndex]} ${seriesName}</div>
              </div>`;
      },
    },
    ...props.options,
  };
});
const loading = ref(true);

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>
<template>
  <v-card class="d-flex flex-grow-1 bg-primary-darken-4 pa-3" theme="dark">
    <!-- loading spinner -->
    <div v-if="loading" class="d-flex flex-grow-1 align-center justify-center">
      <v-progress-circular indeterminate color="green"></v-progress-circular>
    </div>

    <!-- information -->
    <div v-else class="d-flex flex-column flex-grow-1">
      <v-card-title class="d-flex">
        <div class="font-weight-bold">{{label}}</div>
        <v-spacer></v-spacer>
        <v-btn
          variant="text"
          color="primary"
          class="font-weight-bold"
          @click="$emit('action-clicked')"
        >{{ actionLabel }}</v-btn>

      </v-card-title>
      <div class="font-weight-bold" style="color: pink;">{{taskId}}</div>
      <div class="d-flex flex-column flex-grow-1">
        <div class="title mb-1 font-weight-bold">
          {{ $t("monitor.totalDownload")}}
          <span style="float: right">{{useTime}}</span>
        </div>
        <div class="pa-2">
          <div class="text-h4">
            {{ total }}
          </div>
        </div>

        <v-spacer></v-spacer>

        <div class="px-2 pb-2">
          <div class="title mb-1 font-weight-bold">
            {{ $t("monitor.speed") }}
          </div>
          <div class="d-flex align-center">
            <div class="text-h4">
              {{ now }}/s
            </div>
            <v-spacer></v-spacer>
            <div class="d-flex flex-column text-right">
              <span>{{ avg }}/s</span>
              <div class="text-caption"> {{ $t("monitor.avgDownload") }}</div>
            </div>
          </div>
        </div>
      </div>

      <apexchart
        type="area"
        height="120"
        :options="chartOptions"
        :series="series"
      ></apexchart>
    </div>
  </v-card>
</template>
