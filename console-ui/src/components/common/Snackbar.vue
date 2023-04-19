<script setup lang="ts">
import { useSnackbarStore } from "@/stores/snackbarStore";
const snackbarStore = useSnackbarStore();

const getIcon = (type) => {
  const icon = {
    info: "mdi-information",
    success: "mdi-check-circle",
    error: "mdi-alert-circle",
    warning: "mdi-alert",
  };

  return icon[type];
};
</script>
<template>
  <div>
    <v-snackbar
      v-model="snackbarStore.isShow"
      timeout="2000"
      :color="snackbarStore.type"
      class="elevation-10"
      location="top"
      multi-line
    >
      <div class="d-flex align-center">
        <v-icon class="mr-2">{{ getIcon(snackbarStore.type) }}</v-icon>
        <span> {{ snackbarStore.message }}</span>
      </div>

      <template v-slot:actions>
        <v-btn icon variant="text" @click="snackbarStore.isShow = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>
