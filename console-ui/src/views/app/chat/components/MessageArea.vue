<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import Message from "./Message.vue";
import { useChatStore } from "../chatStore";
const chatStore = useChatStore();
</script>

<template>
  <transition-group name="list">
    <Message
      v-for="message in chatStore.chatHistory"
      :key="message.id"
      :message="message"
      class="my-4 d-flex"
    />
  </transition-group>
</template>

<style scoped lang="scss"></style>
