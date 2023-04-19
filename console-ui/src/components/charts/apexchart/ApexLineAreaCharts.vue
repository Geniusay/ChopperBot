<!--
* @Component: ApexLineAreaCharts
* @Maintainer: J.K. Yang
* @Description: ApexLineAreaCharts
-->
<script setup lang="ts">
import { lineAreaChartSpline } from "./ApexChartsData";
</script>
<template>
  <h1 class="pl-5">ApexLineAreaCharts</h1>
  <apexchart
    type="area"
    height="350"
    :options="lineAreaChartSpline.chartOptions"
    :series="lineAreaChartSpline.series"
  ></apexchart>
</template>

<style scoped lang="scss"></style>
