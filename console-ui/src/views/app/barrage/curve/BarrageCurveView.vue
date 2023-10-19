<template>
  <apexchart
    type="area"
    height="350"
    :options="lineAreaChartSpline.chartOptions"
    :series="lineAreaChartSpline.series"
  ></apexchart>
</template>

<script setup lang="ts">
import {
  curveGenerate,
  curveList
} from "@/api/barrage/barrageCurveApi";
import {nanosToFormat} from "@/utils/timeUtils";
import ApexLineAreaCharts from "@/components/charts/apexchart/ApexLineAreaCharts.vue";
const lineAreaChartSpline = ref({
  series: [
    {
      name: "弹幕得分",
      data: [],
    },
    {
      name: "Recurring Payments",
      data: [],
    },
  ],

  chartOptions: {
    grid: {
      show: true,
      borderColor: "rgba(0, 0, 0, .3)",
      strokeDashArray: 3,
      xaxis: {
        lines: {
          show: true,
        },
      },
      yaxis: {
        lines: {
          show: true,
        },
      },
    },
    dataLabels: {
      enabled: false,
    },
    chart: {
      toolbar: {
        show: true,
      },
    },
    stroke: {
      curve: "smooth",
      width: 2,
    },
    fill: {
      type: "gradient",
      opacity: ["0.1", "0.1"],
    },
    xaxis: {
      categories: [],
      labels: {
        style: {
          cssClass: "grey--text lighten-2--text fill-color",
        },
      },
    },
    yaxis: {
      labels: {
        style: {
          cssClass: "grey--text lighten-2--text fill-color",
        },
      },
    },
    markers: {
      size: 3,
    },
    tooltip: {
      x: {
        format: "dd/MM/yy HH:mm",
      },
      theme: "dark",
    },
    legend: {
      show: false,
    },
  },
});

onMounted(async ()=>{
  await curveList().then((res=>{
    const points = res?.data?.list[0]
    lineAreaChartSpline.value.series[0].data = points.points.map(item=>item.pointScore)
    lineAreaChartSpline.value.series[1].data = points.points.map(item=>item.pointScore)
    lineAreaChartSpline.value.chartOptions.xaxis.categories = points.points.map(item=>nanosToFormat(item.startTime).toString())
  }))
})


</script>

<style scoped>

</style>
