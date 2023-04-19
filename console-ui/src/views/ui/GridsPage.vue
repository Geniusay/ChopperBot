<template>
  <v-container>
    <v-row>
      <v-col class="mt-2" cols="12">
        <v-card elevation="5">
          <v-divider class="my-5"></v-divider>
          <v-table class="pa-2 table-card">
            <thead>
              <tr>
                <th class="text-left">Name</th>
                <th class="text-left">Calories</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in desserts" :key="item.name">
                <td>{{ item.name }}</td>
                <td>{{ item.calories }}</td>
              </tr>
            </tbody>
          </v-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script setup lang="ts">
const desserts = [
  {
    name: "Frozen Yogurt",
    calories: 159,
  },
  {
    name: "Ice cream sandwich",
    calories: 237,
  },
  {
    name: "Eclair",
    calories: 262,
  },
  {
    name: "Cupcake",
    calories: 305,
  },
  {
    name: "Gingerbread",
    calories: 356,
  },
  {
    name: "Jelly bean",
    calories: 375,
  },
  {
    name: "Lollipop",
    calories: 392,
  },
  {
    name: "Honeycomb",
    calories: 408,
  },
  {
    name: "Donut",
    calories: 452,
  },
  {
    name: "KitKat",
    calories: 518,
  },
];
</script>
<style lang="scss" scoped>
.table-card {
  font-family: "Quicksand", sans-serif;
}
// .v-data-table {
//   table {
//     padding: 4px;
//     padding-bottom: 8px;

//     th {
//       text-transform: uppercase;
//       white-space: nowrap;
//     }

//     td {
//       border-bottom: 0 !important;
//     }

//     tbody {
//       tr {
//         transition: box-shadow 0.2s, transform 0.2s ;

//         &:not(.v-data-table__selected):hover {
//           box-shadow: 0 3px 15px -2px rgba(0, 0, 0, 0.12);
//           transform: translateY(-4px);
//         }
//       }
//     }
//   }
// }
</style>
