<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import CopyLabel from "@/components/common/CopyLabel.vue";
const props = defineProps({
  // Message to display
  message: {
    type: Object,
    default: () => ({}),
  },
});

const isUserMessage = computed(() => {
  return props.message.user.id === 1;
});
</script>

<template>
  <div
    class="d-flex align-center justify-align-start"
    :class="{ 'flex-row-reverse': isUserMessage }"
  >
    <v-avatar size="40" class="elevation-1 grey lighten-3">
      <v-img v-if="message.user.avatar" :src="message.user.avatar" />
    </v-avatar>
    <v-card
      class="px-3 py-2 mx-2 w-auto"
      :class="isUserMessage ? 'gradient gray' : ''"
      :theme="isUserMessage ? 'dark' : 'light'"
    >
      <copy-label :text="message.text" />
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
