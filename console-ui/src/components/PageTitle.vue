<template>
  <h1 class="text-h4 mt-5">{{ title }}</h1>
</template>

<script setup lang="ts">
const route = useRoute();

const title = ref("");

watchEffect(() => {
  if (route.meta && route.meta.title) {
    title.value = route.meta.title as string;
  }
});
</script>
