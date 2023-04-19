export default [
  {
    path: "/chart/echart-line",
    name: "chart-echart-line",
    component: () =>
      import(
        /* webpackChunkName: "chart-echart-line" */ "@/views/chart/EchartLine.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "Echart Line",
    },
  },
  {
    path: "/chart/echart-bar",
    name: "chart-echart-bar",
    component: () =>
      import(
        /* webpackChunkName: "chart-echart-bar" */ "@/views/chart/EchartBar.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "Echart Bar",
    },
  },
  {
    path: "/chart/echart-pie",
    name: "chart-echart-pie",
    component: () =>
      import(
        /* webpackChunkName: "chart-echart-pie" */ "@/views/chart/EchartPie.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "Echart Pie",
    },
  },
  {
    path: "/chart/echart-scatter",
    name: "chart-echart-scatter",
    component: () =>
      import(
        /* webpackChunkName: "chart-echart-scatter" */ "@/views/chart/EchartScatter.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "Echart Scatter",
    },
  },
  {
    path: "/chart/echart-others",
    name: "chart-echart-others",
    component: () =>
      import(
        /* webpackChunkName: "chart-echart-others" */ "@/views/chart/EchartOthers.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "Echart Others",
    },
  },
  {
    path: "/chart/apexchart",
    name: "chart-apexchart",
    component: () =>
      import(
        /* webpackChunkName: "chart-apexchart" */ "@/views/chart/ApexCharts.vue"
      ),
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "Chart",
      title: "ApexChart",
    },
  },
];
