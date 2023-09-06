<script setup lang="ts">
import CopyLabel from "@/components/common/CopyLabel.vue";
import {Creeper} from "../creeperTypes"

const props = defineProps<{
  creepers: Creeper[];
}>();

const loading = ref(true);

const headers = [
  { text: "Creeper Id", align: "start", value: "id" },
  {
    text: "爬虫名称",
    sortable: false,
    value: "name",
  },
  { text: "描述", value: "description" },
  { text: "作者", value: "author" },
  { text: "启用", value: "discard" },
];

const open = (item) => {};

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>

<template>
  <!-- loading spinner -->
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular indeterminate color="primary"></v-progress-circular>
  </div>
  <div v-else>
    <h6 class="text-h6 font-weight-bold pa-5 d-flex align-center">
      <span class="flex-1">Creeper Library</span>
    </h6>
    <v-table class="pa-3">
      <thead>
      <tr>
        <th class="text-left" v-for="header in headers" :key="header.text">
          {{ header.text }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in creepers" :key="item.name">
        <td class="font-weight-bold">
          <copy-label :text="`# ${index}`" />
        </td>
        <td>
          <copy-label :text="item.name" />
        </td>
        <td>{{ item.description }}</td>
        <td>{{ item.author }}</td>
        <td class="font-weight-bold">
          <div v-if="item.discard" class="text-secondary">
            <v-icon size="small" color="secondary">mdi-circle-medium</v-icon>
            <span>弃用</span>
          </div>
          <div v-else class="text-success">
            <v-icon size="small" color="success">mdi-circle-medium</v-icon>
            <span>启用</span>
          </div>
        </td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>

<style lang="scss" scoped>
.v-table {
  table {
    padding: 4px;
    padding-bottom: 8px;

    th {
      text-transform: uppercase;
      white-space: nowrap;
    }

    td {
      border-bottom: 0 !important;
    }

    tbody {
      tr {
        transition: box-shadow 0.2s, transform 0.2s;

        &:not(.v-data-table__selected):hover {
          box-shadow: 0 3px 15px -2px rgba(0, 0, 0, 0.12);
          transform: translateY(-4px);
        }
      }
    }
  }
}
</style>
