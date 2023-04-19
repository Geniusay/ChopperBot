<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useTodoStore } from "@/views/app/todo/todoStore";
import EmailCompose from "./EmailCompose.vue";
const todoStore = useTodoStore();
</script>

<template>
  <v-card height="100%" class="pa-3">
    <!-- ---------------------------------------------- -->
    <!-- Write Email -->
    <!-- ---------------------------------------------- -->

    <EmailCompose />

    <v-list nav class="mt-2 pa-0">
      <v-list-item
        prepend-icon="mdi-inbox"
        to="/apps/email/inbox"
        active-class="text-primary"
        link
        title="Inbox"
      >
        <template v-slot:append>
          <v-badge color="primary" content="12" inline></v-badge>
        </template>
      </v-list-item>
      <v-list-item
        prepend-icon="mdi-send-outline"
        to="/apps/email/send"
        active-class="text-primary"
        link
        title="Send"
      >
        <template v-slot:append>
          <v-badge color="primary" content="12" inline></v-badge>
        </template>
      </v-list-item>
      <v-list-item
        prepend-icon="mdi-pencil-outline"
        to="/apps/email/drafts"
        active-class="text-primary"
        link
        title="Drafts"
      >
        <template v-slot:append>
          <v-badge color="primary" content="12" inline></v-badge>
        </template>
      </v-list-item>
      <v-list-item
        prepend-icon="mdi-star-outline"
        to="/apps/email/starred"
        active-class="text-primary"
        link
        title="Starred"
      >
        <template v-slot:append>
          <v-badge color="primary" content="12" inline></v-badge>
        </template>
      </v-list-item>
      <v-list-item
        prepend-icon="mdi-delete-restore"
        to="/apps/email/trash"
        active-class="text-primary"
        link
        title="Trash"
      >
        <template v-slot:append>
          <v-badge color="primary" content="12" inline></v-badge>
        </template>
      </v-list-item>
    </v-list>
    <div class="pa-1 mt-2 text-overline text-grey">Labels</div>
    <v-list nav class="mt-2 pa-0">
      <v-list-item
        v-for="label in todoStore.labels"
        active-class="text-primary"
        :to="`/apps/todo/label/${label.id}`"
        link
        :title="label.title"
      >
        <template v-slot:prepend>
          <v-icon :color="label.color">mdi-label-outline </v-icon>
        </template>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<style scoped lang="scss"></style>
