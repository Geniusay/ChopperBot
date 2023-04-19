<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useDisplay } from "vuetify";
import InputArea from "@/views/app/chat/components/InputArea.vue";
import MessageArea from "@/views/app/chat/components/MessageArea.vue";
const dialog = ref(false);
const { xs } = useDisplay();
</script>

<template>
  <v-btn size="50" @click="dialog = !dialog">
    <v-icon size="30">mdi-chat-outline </v-icon>
    <v-tooltip
      activator="parent"
      location="left"
      :text="$t('toolbox.chatAssistant.title')"
    ></v-tooltip>
  </v-btn>

  <teleport to="body">
    <transition name="slide-y">
      <v-card
        v-if="dialog"
        class="dialog-bottom d-flex flex-column"
        :width="xs ? '100%' : '600px'"
        height="500px"
      >
        <v-card-title>
          <span class="flex-1">
            <v-avatar size="40">
              <img
                src="https://img.icons8.com/color/96/null/filled-chat.png"
                alt="alt"
              />
            </v-avatar>

            OpenAi Chat
          </span>

          <v-spacer></v-spacer>
          <v-btn icon @click.stop="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>
        <hr />
        <v-card-text class="overflow-scroll">
          <MessageArea />
        </v-card-text>
        <hr />
        <v-card-actions>
          <InputArea />
        </v-card-actions>
      </v-card>
    </transition>
  </teleport>
</template>

<style scoped lang="scss">
.dialog-bottom {
  z-index: 999;
  position: fixed;
  bottom: 10px;
  right: 0px;
}
</style>
