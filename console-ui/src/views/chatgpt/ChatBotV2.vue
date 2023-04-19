<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useSnackbarStore } from "@/stores/snackbarStore";
import { useChatStore } from "@/views/app/chat/chatStore";
import AnimationAi from "@/components/animations/AnimationBot2.vue";
import { read } from "@/utils/aiUtils";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
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

// Send Messsage
const sendMessage = async () => {
  if (userMessage.value) {
    // Add the message to the list
    messages.value.push({
      content: userMessage.value,
      role: "user",
    });

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
    return;
  }

  try {
    // Create a completion (axios is not used here because it does not support streaming)
    const completion = await fetch(
      "https://api.openai.com/v1/chat/completions",
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${chatStore.getApiKey}`,
        },
        method: "POST",
        body: JSON.stringify({
          messages: messages.value,
          model: "gpt-3.5-turbo",
          stream: true,
        }),
      }
    );

    // Handle errors
    if (!completion.ok) {
      const errorData = await completion.json();
      snackbarStore.showErrorMessage(errorData.error.message);

      return;
    }

    // Create a reader
    const reader = completion.body?.getReader();
    if (!reader) {
      snackbarStore.showErrorMessage("Cannot read the stream.");
    }

    // Add the bot message
    messages.value.push({
      content: "",
      role: "assistant",
    });

    // Read the stream
    read(reader, messages);
  } catch (error) {
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
  <v-card>
    <perfect-scrollbar v-if="messages.length > 0" class="message-container">
      <template v-for="message in messages">
        <div v-if="message.role === 'user'">
          <div class="pa-6 user-message">
            <div class="message align-center">
              <v-avatar class="mr-9">
                <img src="@/assets/images/avatars/avatar_user.jpg" alt="alt" />
              </v-avatar>
              <b> {{ message.content }}</b>
            </div>
          </div>
        </div>
        <div v-else>
          <div class="bg-white pa-6">
            <div class="message">
              <v-avatar class="mr-4 mt-4">
                <img
                  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s"
                  alt="alt"
                />
              </v-avatar>
              <md-editor v-model="message.content" previewOnly />
            </div>
          </div>
        </div>
      </template>
    </perfect-scrollbar>
    <div class="no-message-container" v-else>
      <h1 class="text-h2 text-blue-lighten-1 font-weight-bold">
        Ask Me Any Thing
      </h1>
      <AnimationAi />
    </div>

    <v-sheet elevation="0" class="my-5 mx-auto" max-width="1200">
      <!-- Todo Select Model  -->

      <!-- <div class="mb-2">
        <v-select
          class="w-50"
          label="Model"
          hide-details
          :items="['GPT-4', 'GPT-3.5']"
          variant="solo"
        ></v-select>
      </div> -->
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
  background-color: #f6f6f6;
  border-top: 1px solid #e5e7eb;
  border-bottom: 1px solid #e5e7eb;
}

.message {
  margin: 0 auto;
  max-width: 1200px;
  display: flex;
}

.message-container {
  height: calc(100vh - 330px);
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
</style>
