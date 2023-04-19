<script setup lang="ts">
import Animation404 from "@/components/animations/Animation404.vue";
</script>
<template>
  <v-card
    variant="flat"
    height="100vh"
    class="d-flex justify-center align-center text-center"
  >
    <div>
      <Animation404 />

      <v-btn flat color="#00A9D7" class="mb-4 text-white" to="/"
        >Go Back to Home</v-btn
      >
    </div>
  </v-card>
</template>
