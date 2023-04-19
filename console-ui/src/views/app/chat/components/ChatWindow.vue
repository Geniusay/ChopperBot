<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import InputArea from "./InputArea.vue";
import MessageArea from "./MessageArea.vue";
import { useChatStore } from "../chatStore";

const chatStore = useChatStore();
const target = ref();

// scroll to bottom
const scrollToBottom = () => {
  const chatArea = document.getElementById("chat-area");
  chatArea?.scrollTo({
    top: chatArea?.scrollHeight,
  });
};
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!---Header Area -->
  <!-- ---------------------------------------------- -->
  <v-toolbar color="white" rounded="lg">
    <div class="mx-2 pa-1 bg-primary flex-grow-1 rounded-sm rounded-lg">
      <v-btn size="large" color="white" @click="chatStore.clearHistory"
        >清空记录</v-btn
      >
    </div>
  </v-toolbar>
  <!-- ---------------------------------------------- -->
  <!---Chat Area -->
  <!-- ---------------------------------------------- -->
  <perfect-scrollbar ref="target" id="chat-area" class="chat-area">
    <!-- ---------------------------------------------- -->
    <!---Message Area -->
    <!-- ---------------------------------------------- -->
    <v-sheet elevation="0" color="transparent" class="messages-area pa-5">
      <MessageArea />
    </v-sheet>
    <!-- ---------------------------------------------- -->
    <!---Input Area -->
    <!-- ---------------------------------------------- -->
  </perfect-scrollbar>
  <v-sheet class="input-area">
    <InputArea @scroll="scrollToBottom" />
  </v-sheet>
</template>

<style scoped lang="scss">
.chat-area {
  height: calc(100vh - 400px);
  background-image: url("@/assets/images/chat-bg-2.png");
  background-repeat: repeat;

  .input-area {
    position: fixed;
    width: 100%;
    height: 300px;
    bottom: 0;
    left: 0;
    background-color: #fff;
  }
}
</style>
