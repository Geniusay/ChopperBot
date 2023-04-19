<script setup lang="ts">
import { ref, onMounted } from "vue";

import BarChart1 from "@/views/chart/component/BarChart1.vue";
const loading = ref(true);

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
    <h6 class="text-h6 d-flex align-center font-weight-bold">
      <span class="pa-5">Chart</span>
    </h6>
    <v-card class="" variant="flat">
      <BarChart1 />
    </v-card>
  </div>
</template>

<style lang="scss" scoped></style>
