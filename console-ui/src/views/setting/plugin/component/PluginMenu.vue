<script setup lang="ts">
import { usePluginStore } from "@/views/setting/plugin/pluginStore";
const pluginStore = usePluginStore();
</script>

<template>

  <v-card height="100%" class="pa-3">
    <!-- ---------------------------------------------- -->
    <!-- Add Task Dialog -->
    <!-- ---------------------------------------------- -->
    <v-btn color="primary" block size="large" class="mb-3">ChopperBot Plugin</v-btn>

    <v-list nav class="mt-2 pa-0">
      <v-list-item
        prepend-icon="mdi-calendar-check"
        to="/setting/plugin_setting/plugins"
        active-class="text-primary"
        link
        title="Plugins"
      >
        <template v-slot:append>
          <v-badge
            color="primary"
            :content="pluginStore.getAllList.length"
            inline
          ></v-badge>
        </template>
      </v-list-item>

      <v-list-item
        prepend-icon="mdi mdi-electron-framework"
        to="/setting/plugin_setting/running"
        active-class="text-primary"
        link
        title="Running"
      >
        <template v-slot:append>
          <v-badge
            color="green"
            :content="pluginStore.getStartList.length"
            inline
          ></v-badge>
        </template>
      </v-list-item>

      <v-list-item
        prepend-icon="mdi mdi-refresh-auto"
        to="/setting/plugin_setting/autoStart"
        active-class="text-primary"
        link
        title="AutoStart"
      >
        <template v-slot:append>
          <v-badge
            color="blue"
            :content="pluginStore.getAutoStartList.length"
            inline
          ></v-badge>
        </template>
      </v-list-item>
    </v-list>
  </v-card>
</template>
