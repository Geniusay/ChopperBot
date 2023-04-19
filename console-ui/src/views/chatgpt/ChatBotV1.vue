<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useSnackbarStore } from "@/stores/snackbarStore";
import { useChatStore } from "@/views/app/chat/chatStore";
import AnimationAi from "@/components/animations/AnimationBot1.vue";

import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { createCompletionApi } from "@/api/aiApi";
const snackbarStore = useSnackbarStore();
const chatStore = useChatStore();

interface Message {
  content: string;
  role: "user" | "assistant";
}

// Message List
const messages = ref<Message[]>([]);

// User Input Message
const userMessage = ref("");

const isLoading = ref(false);

// Send Messsage
const sendMessage = async () => {
  if (userMessage.value) {
    // Add the message to the list
    messages.value.push({
      content: userMessage.value,
      role: "user",
    });

    isLoading.value = true;
    // Create a completion
    await createCompletion();

    // Clear the input
    userMessage.value = "";
  }
};

const createCompletion = async () => {
  // Check if the API key is set
  if (!chatStore.getApiKey) {
    snackbarStore.showErrorMessage("请先输入API KEY");
    isLoading.value = false;
    return;
  }

  try {
    const completion = await createCompletionApi(
      {
        messages: messages.value,
        model: "gpt-3.5-turbo",
      },
      chatStore.getApiKey
    );

    isLoading.value = false;

    // Add the bot message
    messages.value.push({
      content: completion.data.choices[0].message.content,
      role: "assistant",
    });
  } catch (error) {
    isLoading.value = false;
    snackbarStore.showErrorMessage(error.message);
  }
};

// Scroll to the bottom of the message container
const scrollToBottom = () => {
  const container = document.querySelector(".message-container");
  setTimeout(() => {
    container?.scrollTo({
      top: container?.scrollHeight,
    });
  }, 100);
};

watch(
  () => messages.value,
  (val) => {
    if (val) {
      scrollToBottom();
    }
  },
  {
    deep: true,
  }
);
</script>

<template>
  <v-card class="bg">
    <perfect-scrollbar v-if="messages.length > 0" class="message-container">
      <template v-for="message in messages">
        <div v-if="message.role === 'user'">
          <div class="pa-6 user-message">
            <v-avatar class="ml-4" rounded="sm" variant="elevated">
              <img src="@/assets/images/avatars/avatar_user.jpg" alt="alt" />
            </v-avatar>
            <v-card class="gradient gray" theme="dark">
              <v-card-text>
                <b> {{ message.content }}</b></v-card-text
              >
            </v-card>
          </div>
        </div>
        <div v-else>
          <div class="pa-6 assistant-message">
            <v-avatar class="mr-4" rounded="sm" variant="elevated">
              <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s"
                alt="alt"
              />
            </v-avatar>
            <v-card>
              <v-card-text>
                <md-editor v-model="message.content" previewOnly />
              </v-card-text>
            </v-card>
          </div>
        </div>
      </template>
      <div v-if="isLoading">
        <div class="pa-6">
          <div class="message">
            <AnimationAi :size="100" />
          </div>
        </div>
      </div>
    </perfect-scrollbar>

    <div class="no-message-container" v-else>
      <h1 class="text-h2 text-blue-lighten-1 font-weight-bold">Chat With Me</h1>
      <AnimationAi />
    </div>
    <v-sheet elevation="0" class="my-5 mx-auto" max-width="1200">
      <!-- Todo Select Model  -->

      <v-text-field
        color="primary"
        ref="input"
        v-model="userMessage"
        placeholder="SendMessage"
        hide-details
        @keyup.enter="sendMessage"
      >
        <template #prepend-inner>
          <v-icon>mdi-microphone</v-icon>
        </template>

        <template #append-inner>
          <v-icon @click="sendMessage">mdi-send</v-icon>
        </template>
      </v-text-field>
    </v-sheet>
  </v-card>
</template>

<style scoped lang="scss">
.user-message {
  display: flex;
  align-content: center;
  justify-content: end;
  flex-direction: row-reverse;
}

.assistant-message {
  display: flex;
  align-content: center;
  justify-content: start;
  flex-direction: row;
}

.message {
  margin: 0 auto;
  max-width: 1200px;
  display: flex;
}

.message-container {
  height: calc(100vh - 330px);
  background-image: url("@/assets/images/chat-bg-2.png");
  background-repeat: repeat;
}

.no-message-container {
  height: calc(100vh - 330px);
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  h1 {
    font-size: 2rem;
    font-weight: 500;
  }
}

.bg {
  background-image: url("@/assets/images/chat-bg-2.png");
  background-repeat: repeat;
}
</style>
