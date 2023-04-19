<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useCustomizeThemeStore } from "@/stores/customizeTheme";
import ToolbarLanguage from "@/components/toolbar/ToolbarLanguage.vue";
import ToolbarNotifications from "./ToolbarNotifications.vue";
import ToolbarUser from "./ToolbarUser.vue";
import { useTodoStore } from "@/views/app/todo/todoStore";

const todoStore = useTodoStore();
const customizeTheme = useCustomizeThemeStore();
const showMobileSearch = ref(false);
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!--App Bar -->
  <!-- ---------------------------------------------- -->
  <v-app-bar>
    <!-- search input mobile -->
    <div class="d-flex flex-grow-1 align-center" v-if="showMobileSearch">
      <v-text-field
        color="primary"
        class="elevation-1"
        variant="solo"
        prepend-inner-icon="mdi-magnify"
        append-inner-icon="mdi-close"
        @click:append-inner="showMobileSearch = false"
        hide-details
        placeholder="Search"
      ></v-text-field>
    </div>
    <div v-else class="d-flex flex-grow-1 align-center">
      <v-app-bar-nav-icon
        @click="customizeTheme.mainSidebar = !customizeTheme.mainSidebar"
      ></v-app-bar-nav-icon>
      <v-toolbar-title></v-toolbar-title>

      <v-spacer></v-spacer>
      <v-btn class="d-block d-md-none" icon @click="showMobileSearch = true">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <!-- search input desktop -->

      <v-text-field
        color="primary"
        class="d-none d-md-block elevation-1"
        variant="solo"
        prepend-inner-icon="mdi-magnify"
        hide-details
        placeholder="Search"
      ></v-text-field>

      <v-btn class="text-none" stacked>
        <v-badge dot color="success">
          <v-icon>mdi-account-multiple-outline</v-icon>
        </v-badge>
      </v-btn>

      <v-btn to="/apps/todo" class="text-none" stacked>
        <v-badge :content="`${todoStore.getTodoList.length} +`" color="error">
          <v-icon>mdi-calendar-check</v-icon>
        </v-badge>
      </v-btn>
      <ToolbarLanguage />
      <ToolbarNotifications />
      <ToolbarUser />
    </div>
  </v-app-bar>
</template>

<style scoped lang="scss"></style>
