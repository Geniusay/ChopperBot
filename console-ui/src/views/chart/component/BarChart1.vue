<!--
* @Component: EchartPie
* @Maintainer: J.K. Yang
* @Description: Echart 饼图页
-->
\
<script setup lang="ts">
import { Ref } from "vue";
import type { EChartsOption } from "echarts";
import echarts, { useChart, RenderType, ThemeType } from "@/plugins/echarts";

// Generate data
let category: string[] = [];
let dottedBase = +new Date();
let lineData: any[] = [];
let barData: any[] = [];
for (let i = 0; i < 20; i++) {
  let date = new Date((dottedBase += 3600 * 24 * 1000));
  const dateStr = [
    date.getFullYear(),
    date.getMonth() + 1,
    date.getDate(),
  ].join("-");
  category.push(dateStr);
  let b = Math.random() * 200;
  let d = Math.random() * 200;
  barData.push(b);
  lineData.push(d + b);
}
const option = computed<EChartsOption>(() => ({
  backgroundColor: "#0f375f",
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
    },
  },
  legend: {
    data: ["line", "bar", "line", "dotted"],
    textStyle: {
      color: "#ccc",
    },
    padding: [30, 0, 0, 0],
  },
  xAxis: {
    data: category,
    axisLine: {
      lineStyle: {
        color: "#ccc",
      },
    },
  },
  yAxis: {
    splitLine: { show: false },
    axisLine: {
      lineStyle: {
        color: "#ccc",
      },
    },
  },
  series: [
    {
      name: "line",
      type: "line",
      smooth: true,
      showAllSymbol: true,
      symbol: "emptyCircle",
      symbolSize: 15,
      data: lineData,
    },
    {
      name: "bar",
      type: "bar",
      barWidth: 10,
      itemStyle: {
        borderRadius: 5,
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: "#14c8d4" },
          { offset: 1, color: "#43eec6" },
        ]),
      },
      data: barData,
    },
    {
      name: "line",
      type: "bar",
      barGap: "-100%",
      barWidth: 10,
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: "rgba(20,200,212,0.5)" },
          { offset: 0.2, color: "rgba(20,200,212,0.2)" },
          { offset: 1, color: "rgba(20,200,212,0)" },
        ]),
      },
      z: -12,
      data: lineData,
    },
    {
      name: "dotted",
      type: "pictorialBar",
      symbol: "rect",
      itemStyle: {
        color: "#0f375f",
      },
      symbolRepeat: true,
      symbolSize: [12, 4],
      symbolMargin: 1,
      z: -10,
      data: lineData,
    },
  ],
}));

const chartEl = ref<HTMLDivElement | null>(null);

const { setOption, showLoading } = useChart(
  chartEl as Ref<HTMLDivElement>,
  true,
  true,
  RenderType.SVGRenderer,
  ThemeType.Light
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
  <v-card height="360">
    <div ref="chartEl" :style="{ width: `100%`, height: `100%` }"></div>
  </v-card>
</template>
