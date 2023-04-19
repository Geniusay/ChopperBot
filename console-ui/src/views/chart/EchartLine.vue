<!--
* @Component: EchartPie
* @Maintainer: J.K. Yang
* @Description: Echart 饼图页
-->
\
<script setup lang="ts">
import { Ref } from "vue";
import type { EChartsOption } from "echarts";
import { useChart, RenderType, ThemeType } from "@/plugins/echarts";

const data = [
  ["2000-06-05", 116],
  ["2000-06-06", 129],
  ["2000-06-07", 135],
  ["2000-06-08", 86],
  ["2000-06-09", 73],
  ["2000-06-10", 85],
  ["2000-06-11", 73],
  ["2000-06-12", 68],
  ["2000-06-13", 92],
  ["2000-06-14", 130],
  ["2000-06-15", 245],
  ["2000-06-16", 139],
  ["2000-06-17", 115],
  ["2000-06-18", 111],
  ["2000-06-19", 309],
  ["2000-06-20", 206],
  ["2000-06-21", 137],
  ["2000-06-22", 128],
  ["2000-06-23", 85],
  ["2000-06-24", 94],
  ["2000-06-25", 71],
  ["2000-06-26", 106],
  ["2000-06-27", 84],
  ["2000-06-28", 93],
  ["2000-06-29", 85],
  ["2000-06-30", 73],
  ["2000-07-01", 83],
  ["2000-07-02", 125],
  ["2000-07-03", 107],
  ["2000-07-04", 82],
  ["2000-07-05", 44],
  ["2000-07-06", 72],
  ["2000-07-07", 106],
  ["2000-07-08", 107],
  ["2000-07-09", 66],
  ["2000-07-10", 91],
  ["2000-07-11", 92],
  ["2000-07-12", 113],
  ["2000-07-13", 107],
  ["2000-07-14", 131],
  ["2000-07-15", 111],
  ["2000-07-16", 64],
  ["2000-07-17", 69],
  ["2000-07-18", 88],
  ["2000-07-19", 77],
  ["2000-07-20", 83],
  ["2000-07-21", 111],
  ["2000-07-22", 57],
  ["2000-07-23", 55],
  ["2000-07-24", 60],
];
const dateList = data.map(function (item) {
  return item[0];
});
const valueList = data.map(function (item) {
  return item[1];
});

const option = computed<EChartsOption>(() => ({
  // Make gradient line here
  visualMap: [
    {
      show: false,
      type: "continuous",
      seriesIndex: 0,
      min: 0,
      max: 400,
    },
    {
      show: false,
      type: "continuous",
      seriesIndex: 1,
      dimension: 0,
      min: 0,
      max: dateList.length - 1,
    },
  ],
  title: [
    {
      left: "center",
      text: "Gradient along the y axis",
    },
    {
      top: "55%",
      left: "center",
      text: "Gradient along the x axis",
    },
  ],
  tooltip: {
    trigger: "axis",
  },
  xAxis: [
    {
      data: dateList,
    },
    {
      data: dateList,
      gridIndex: 1,
    },
  ],
  yAxis: [
    {},
    {
      gridIndex: 1,
    },
  ],
  grid: [
    {
      bottom: "60%",
    },
    {
      top: "60%",
    },
  ],
  series: [
    {
      type: "line",
      showSymbol: false,
      data: valueList,
    },
    {
      type: "line",
      showSymbol: false,
      data: valueList,
      xAxisIndex: 1,
      yAxisIndex: 1,
    },
  ],
}));

const chartEl = ref<HTMLDivElement | null>(null);

const { setOption, showLoading } = useChart(
  chartEl as Ref<HTMLDivElement>,
  true,
  true,
  RenderType.SVGRenderer,
  ThemeType.Dark
);

onMounted(() => {
  nextTick(() => {
    // 显示loading
    showLoading();
    // 假装有网络请求 ...
    // 渲染图表
    setOption(option.value);
  });
});

watch(
  () => option.value,
  (newVal) => {
    setOption(newVal);
  },
  { deep: true }
);
</script>

<template>
  <v-card class="ma-5 pa-5">
    <div ref="chartEl" :style="{ width: `100%`, height: `800px` }"></div>
  </v-card>
  <v-card class="ma-5 pa-5 control panel" min-height="500">
    <h1 class="text-h5 my-5">Control Panel</h1>
    <!-- <v-btn class="mr-5" color="primary" @click="textTitle = 'new Title'"
      >Change Title</v-btn
    >
    <v-btn class="mr-5" color="primary" @click="add">Add Data</v-btn> -->
  </v-card>
</template>
