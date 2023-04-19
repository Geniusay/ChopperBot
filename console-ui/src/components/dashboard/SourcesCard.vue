<template>
  <h6 class="text-h6 pa-5 d-flex align-center">
    <span class="flex-1 font-weight-bold">Traffic Sources</span>
  </h6>

  <v-chart class="chart" :option="option" autoresize />
</template>

<script setup lang="ts">
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { PieChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from "echarts/components";
import VChart from "vue-echarts";

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);

const option = ref({
  color: ["#73BFB8", "#3BA2D5", "#2364AA", "#FEC601", "#0096C7"],
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b} : {c} ({d}%)",
  },
  legend: {
    // orient: "vertical",
    top: "0%",
    left: "center",
    data: ["Direct", "Email", "Ad Networks", "Video Ads", "Search Engines"],
  },
  series: [
    {
      name: "Access From",
      type: "pie",
      radius: ["40%", "70%"],
      center: ["50%", "50%"],
      data: [
        { value: 335, name: "Direct" },
        { value: 310, name: "Email" },
        { value: 234, name: "Ad Networks" },
        { value: 135, name: "Video Ads" },
        { value: 1548, name: "Search Engines" },
      ],
      label: {
        show: false,
        position: "center",
      },
      labelLine: {
        show: false,
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 20,
          fontWeight: "bold",
        },
      },
      itemStyle: {
        borderRadius: 2,
        borderColor: "#fff",
        borderWidth: 2,
      },
    },
  ],
});
</script>

<style lang="scss" scoped>
.chart {
  height: 360px;
}
</style>
