<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import ApiKeyDialog from "@/components/ApiKeyDialog.vue";
import { useChatStore } from "../chatStore";
const chatStore = useChatStore();
</script>

<template>
  <v-card height="100%" class="pa-3">
    <!-- ---------------------------------------------- -->
    <!-- Add Task Dialog -->
    <!-- ---------------------------------------------- -->
    <v-btn
      variant="elevated"
      color="primary"
      block
      size="large"
      class="text-white mb-2"
      @click="chatStore.apiKeyDialog = true"
      >API KEY</v-btn
    >
    <ApiKeyDialog />
    <v-btn color="primary" block size="large" class="mb-3">New Chat</v-btn>

    <v-list nav class="mt-2 pa-0" color="primary">
      <v-list-item
        to="/apps/chat/base-channel"
        active-class="active-chat"
        link
        title="摩诃善法大吉祥智慧主"
      >
        <template v-slot:prepend>
          <v-avatar class="mx-5" size="40">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s"
              alt="alt"
            />
          </v-avatar>
        </template>
      </v-list-item>
    </v-list>
    <!-- <v-list nav class="mt-2 pa-0">
      <v-list-item to="" active-class="active-chat" link title="七叶寂照秘密主">
        <template v-slot:prepend>
          <v-avatar class="mx-5" size="40">
            <img
              src="https://i.pinimg.com/564x/85/42/c1/8542c171215f7b7499b905824cd5096f.jpg"
              alt="alt"
            />
          </v-avatar>
        </template>
      </v-list-item>
    </v-list> -->
    <!-- <div class="pa-1 mt-2 text-overline text-grey">More Chats</div> -->
  </v-card>
</template>

<style scoped lang="scss">
.active-chat {
  border-left: 5px solid;
  border-image-slice: 1;
  border-image-source: linear-gradient(to bottom, #3a456c, #a4abbb);
}
</style>
