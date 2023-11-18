<template>
  <apexchart
    type="area"
    height="350"
    :options="lineAreaChartSpline.chartOptions"
    :series="lineAreaChartSpline.series"
  ></apexchart>
  <v-row class="my-2">
    <v-col cols="6">
      <div class="first-div">
        <v-card height="100%">
          <v-list :items="items"></v-list>
        </v-card>
      </div>
    </v-col>
    <v-col cols="6">
      <div class="second-div">
        <v-card height="100%" class="mx-auto">
          <v-toolbar color="transparent" class="px-0">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title>{{ title }}</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon>
              <v-icon>mdi mdi-cog</v-icon>
            </v-btn>

            <template v-slot:extension>
              <v-tabs
                v-model="tabs"
                color="primary"
                grow
              >
                <v-tab
                  :value="1"
                >
                  <v-icon>mdi mdi-monitor-dashboard</v-icon>
                </v-tab>
                <v-tab
                  :value="2"
                >
                  <v-icon>mdi mdi-chart-pie</v-icon>
                </v-tab>
                <v-tab
                  :value="3"
                >
                  <v-icon>mdi mdi-notebook-multiple</v-icon>
                </v-tab>
              </v-tabs>
            </template>
          </v-toolbar>

          <v-window v-model="tabs">
            <v-window-item
              :key="1"
              :value="1"
            >
              <v-card>
                <v-card-text>
                  <iframe
                    width="100%"
                    height="600"
                    src=" E:\Project\ChopperBot\config\Barrage\online\huya"
                    frameborder="0"
                  ></iframe>
                </v-card-text>
              </v-card>
            </v-window-item>
            <v-window-item
              :key="2"
              :value="2"
            >
              <v-card>
               <BarrageDataLabels></BarrageDataLabels>
              </v-card>
            </v-window-item>
            <v-window-item
              :key="3"
              :value="3"
            >
              <v-card>
                <v-card-text>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                  dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                </v-card-text>
              </v-card>
            </v-window-item>
          </v-window>
        </v-card>
      </div>
    </v-col>
  </v-row>

</template>

<script setup lang="ts">
import {
  curveGenerate,
  curveList
} from "@/api/barrage/barrageCurveApi";
import {nanosToFormat} from "@/utils/timeUtils";
import ApexLineAreaCharts from "@/components/charts/apexchart/ApexLineAreaCharts.vue";
import BarrageDataLabels from "@/views/app/barrage/curve/compus/BarrageDataLabels.vue";
const tabs = ref(1);
const titles = ["监控台","弹幕统计","关键词"]
const title = ref(titles[tabs.value-1])
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
    const points = res?.data?.list[1]
    lineAreaChartSpline.value.series[0].data = points.points.map(item=>item.pointScore)
    lineAreaChartSpline.value.series[1].data = points.points.map(item=>item.pointScore)
    lineAreaChartSpline.value.chartOptions.xaxis.categories = points.points.map(item=>nanosToFormat(item.startTime).toString())
  }))
})

watch(tabs,(newValue,oldValue)=>{
  title.value = titles[tabs.value-1]
})
</script>

<style scoped>

</style>
