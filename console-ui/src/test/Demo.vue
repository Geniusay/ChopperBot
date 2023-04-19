<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
defineProps({
  name: {
    type: String,
    default: "J.K. Yang",
  },
});
</script>

<template>
  <div class="">
    {{ name }}
  </div>
</template>

<style scoped lang="scss"></style>
