const themeColors = ["#ee8a6a", "#0cb9c5", "#fec90f", "#05b187", "#fc4b6c"];
const themeColors2 = ["#4782FB", "#47C4F4", "#fec90f", "#05b187", "#fc4b6c"];

function generateDataHeatMap(count: any, yrange: any) {
  var i = 0;
  var series: any[] = [];
  while (i < count) {
    var x = "w" + (i + 1).toString();
    var y =
      Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;

    series.push({
      x: x,
      y: y,
    });
    i++;
  }
  return series;
}

export const heatMapChart = {
  series: [
    {
      name: "Metric1",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric2",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric3",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric4",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric5",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric6",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
    {
      name: "Metric7",
      data: generateDataHeatMap(18, {
        min: 0,
        max: 90,
      }),
    },
  ],
  chartOptions: {
    dataLabels: {
      enabled: false,
    },
    colors: ["#1e88e5"],
    tooltip: {
      theme: "dark",
    },
  },
};

export const lineAreaChartSpline = {
  series: [
    {
      name: "Open Rate",
      data: [0, 5, 6, 8, 25, 9, 8, 24],
    },
    {
      name: "Recurring Payments",
      data: [0, 3, 1, 2, 8, 1, 5, 1],
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
    colors: themeColors2,
    fill: {
      type: "gradient",
      opacity: ["0.1", "0.1"],
    },
    xaxis: {
      categories: ["1", "2", "3", "4", "5", "6", "7", "8"],
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
};
