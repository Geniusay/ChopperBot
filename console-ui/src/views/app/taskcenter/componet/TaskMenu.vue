<script setup lang="ts">
  import {useTaskStore} from "@/views/app/taskcenter/taskStore";

  const taskStore = useTaskStore()
</script>

<template>
  <v-card height="100%" class="pa-3">
    <!-- ---------------------------------------------- -->
    <!-- Add Task Dialog -->
    <!-- ---------------------------------------------- -->
    <v-btn color="success" block size="large" class="mb-3">TaskCenter Setting</v-btn>
    <div class="pa-1 mt-2 text-overline text-grey">Status</div>
    <v-list nav class="mt-2 pa-0">
      <v-list-item
        v-for="label in taskStore.labels"
        active-class="text-primary"
        :to="`/apps/todo/label/${label.id}`"
        link
        :title="label.title"
      >
        <template v-slot:prepend>
          <v-icon :color="label.color">{{label.icon}}</v-icon>
        </template>
      </v-list-item>
    </v-list>
  </v-card>
</template>
