<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import { useUserStore } from "@/views/data/user/userStore";
import AddAccountDialog from "@/views/data/user/component/AddAccountDialog.vue";
const userStore = useUserStore();
var addAccountDialog = false
</script>

<template>
  <v-card height="100%" class="pa-3">
    <!-- ---------------------------------------------- -->
    <!-- Add Task Dialog -->
    <!-- ---------------------------------------------- -->
    <AddAccountDialog v-if="this.addAccountDialog"></AddAccountDialog>
    <v-btn block class="mb-3" color="primary" size="large" @click="this.addAccountDialog = true">添加账号</v-btn>

    <v-list nav class="mt-2 pa-0">
      <v-list-item
        prepend-icon="mdi-calendar-check"
        to="/apps/user/tasks"
        active-class="text-primary"
        link
        title="Tasks"
      >
        <template v-slot:append>
          <v-badge
            color="primary"
            :content="userStore.getUserList.length"
            inline
          ></v-badge>
        </template>
      </v-list-item>
      <v-list-item
        prepend-icon="mdi-check"
        to="/apps/user/completed"
        active-class="text-primary"
        link
        title="Completed"
      >
        <template v-slot:append>
          <v-badge
            color="primary"
            :content="userStore.getCompletedUsers.length"
            inline
          ></v-badge>
        </template>
      </v-list-item>
    </v-list>
    <div class="pa-1 mt-2 text-overline text-grey">Labels</div>
    <v-list nav class="mt-2 pa-0">
      <v-list-item
        v-for="label in userStore.labels"
        active-class="text-primary"
        :to="`/apps/user/label/${label.id}`"
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
