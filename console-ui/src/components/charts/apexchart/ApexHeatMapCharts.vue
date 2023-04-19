<!--
* @Component: ApexHeatMapCharts
* @Maintainer: J.K. Yang
* @Description: ApexHeatMapCharts
-->
<script setup lang="ts">
import { heatMapChart } from "./ApexChartsData";
</script>
<template>
  <div class="">ApexHeatMapCharts</div>
  <apexchart
    type="heatmap"
    height="310"
    :options="heatMapChart.chartOptions"
    :series="heatMapChart.series"
  ></apexchart>
</template>

<style scoped lang="scss"></style>
