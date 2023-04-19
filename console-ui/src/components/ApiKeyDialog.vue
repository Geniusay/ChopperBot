<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useChatStore } from "@/views/app/chat/chatStore";

const chatStore = useChatStore();

const close = () => {
  chatStore.apiKeyDialog = false;
};

const key = computed({
  get: () => chatStore.apiKey,
  set: (value) => {
    chatStore.saveApiKey(value); // 假设您有一个名为setApiKey的Pinia store mutation
  },
});

const apiKeyShow = ref(false);
</script>

<template>
  <v-dialog v-model="chatStore.apiKeyDialog" width="600">
    <v-card>
      <v-card-title class="font-weight-bold pa-5">
        Input your Api Key</v-card-title
      >
      <hr />
      <v-card-text>
        <v-label class="font-weight-medium mb-2 ml-2">YOUR API KEY</v-label>
        <v-text-field
          color="primary"
          variant="outlined"
          v-model="key"
          class="px-2 py-1"
          placeholder="If not input , the ApiKey in the .env will be used."
          prepend-inner-icon="mdi-key"
          autofocus
          clearable
          hide-details
          @click:prepend-inner="apiKeyShow = !apiKeyShow"
        ></v-text-field>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn variant="flat" color="primary" @click="close">OK</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped lang="scss"></style>
