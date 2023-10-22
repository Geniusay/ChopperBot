<template>
  <div id="chart">
    <apexchart type="bar" height="380" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script setup lang="ts">

const series = [{
  data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380]
}]

const chartOptions = {
  chart: {
    type: 'bar',
      height: 380
  },
  plotOptions: {
    bar: {
      barHeight: '100%',
        distributed: true,
        horizontal: true,
        dataLabels: {
        position: 'bottom'
      },
    }
  },
  colors: ['#33b2df', '#546E7A', '#d4526e', '#13d8aa', '#A5978B', '#2b908f', '#f9a3a4', '#90ee7e',
    '#f48024', '#69d2e7'
  ],
    dataLabels: {
    enabled: true,
      textAnchor: 'start',
      style: {
      colors: ['#fff']
    },
    formatter: function (val, opt) {
      return opt.w.globals.labels[opt.dataPointIndex] + ":  " + val
    },
    offsetX: 0,
      dropShadow: {
      enabled: true
    }
  },
  stroke: {
    width: 1,
      colors: ['#fff']
  },
  xaxis: {
    categories: ['South Korea', 'Canada', 'United Kingdom', 'Netherlands', 'Italy', 'France', 'Japan',
      'United States', 'China', 'India'
    ],
  },
  yaxis: {
    labels: {
      show: false
    }
  },
  title: {
    text: '热点弹幕',
      align: 'center',
      floating: true
  },
  subtitle: {
    text: '统计该弹幕文件出现前10的弹幕',
      align: 'center',
  },
}
</script>

<style scoped>

</style>
