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

const option = computed<EChartsOption>(() => ({
  title: {
    text: "Rainfall vs Evaporation",
    subtext: "Fake Data",
  },
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["Rainfall", "Evaporation"],
  },
  toolbox: {
    show: true,
    feature: {
      dataView: { show: true, readOnly: false },
      magicType: { show: true, type: ["line", "bar"] },
      restore: { show: true },
      saveAsImage: { show: true },
    },
  },
  calculable: true,
  xAxis: [
    {
      type: "category",
      // prettier-ignore
      data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    },
  ],
  yAxis: [
    {
      type: "value",
    },
  ],
  series: [
    {
      name: "Rainfall",
      type: "bar",
      data: [
        2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3,
      ],
      markPoint: {
        data: [
          { type: "max", name: "Max" },
          { type: "min", name: "Min" },
        ],
      },
      markLine: {
        data: [{ type: "average", name: "Avg" }],
      },
    },
    {
      name: "Evaporation",
      type: "bar",
      data: [
        2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3,
      ],
      markPoint: {
        data: [
          { name: "Max", value: 182.2, xAxis: 7, yAxis: 183 },
          { name: "Min", value: 2.3, xAxis: 11, yAxis: 3 },
        ],
      },
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: "#2378f7" },
          { offset: 0.7, color: "#2378f7" },
          { offset: 1, color: "#83bff6" },
        ]),
      },
      markLine: {
        data: [{ type: "average", name: "Avg" }],
      },
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
  <v-card class="ma-5 pa-5">
    <div ref="chartEl" :style="{ width: `100%`, height: `800px` }"></div>

    <h1 class="text-h5 my-5">Control Panel</h1>
  </v-card>
</template>
