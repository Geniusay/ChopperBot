<!--
* @Component: ToolbarNotifications
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
const messages = [
  {
    title: "Brunch this weekend?",
    color: "primary",
    icon: "mdi-account-circle",
    subtitle:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sint, repudiandae?",
    time: "3 min",
  },
  {
    title: "Summer BBQ",
    color: "success",
    icon: "mdi-email-outline",
    subtitle:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sint, repudiandae?",
    time: "3 min",
  },
  {
    title: "Oui oui",
    color: "teal lighten-1",
    icon: "mdi-airplane-landing",
    subtitle:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sint, repudiandae?",
    time: "4 min",
  },
  {
    title: "Disk capacity is at maximum",
    color: "teal accent-3",
    icon: "mdi-server",
    subtitle:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sint, repudiandae?",
    time: "3 hr",
  },
  {
    title: "Recipe to try",
    color: "blue-grey lighten-2",
    icon: "mdi-noodles",
    subtitle:
      "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Sint, repudiandae?",
    time: "8 hr",
  },
];
</script>

<template>
  <v-menu location="bottom right" transition="slide-y-transition">
    <!-- ---------------------------------------------- -->
    <!-- Activator Btn -->
    <!-- ---------------------------------------------- -->
    <template v-slot:activator="{ props }">
      <v-btn icon v-bind="props" class="text-none">
        <v-badge content="2" color="error">
          <v-icon>mdi-bell-outline</v-icon>
        </v-badge>
      </v-btn>
    </template>
    <v-list elevation="1" lines="three" density="compact" max-width="400">
      <v-list-subheader>Notifications</v-list-subheader>
      <v-list-item v-for="(message, i) in messages" :key="i" @click="">
        <!-- ---------------------------------------------- -->
        <!-- Prepend-->
        <!-- ---------------------------------------------- -->
        <template v-slot:prepend>
          <v-avatar size="40" :color="message.color">
            <v-icon color="white">{{ message.icon }}</v-icon>
          </v-avatar>
        </template>
        <!-- ---------------------------------------------- -->
        <!-- Append-->
        <!-- ---------------------------------------------- -->
        <template v-slot:append>
          <div class="full-h d-flex align-center">
            <span class="text-body-2 text-grey"> {{ message.time }}</span>
          </div>
        </template>
        <!-- ---------------------------------------------- -->
        <!-- Main Content-->
        <!-- ---------------------------------------------- -->
        <div>
          <v-list-item-title class="font-weight-bold text-primary">{{
            message.title
          }}</v-list-item-title>
          <v-list-item-subtitle>{{ message.subtitle }}</v-list-item-subtitle>
        </div>
      </v-list-item>
      <!-- ---------------------------------------------- -->
      <!-- See all Btn-->
      <!-- ---------------------------------------------- -->
      <div class="text-center py-5">
        <v-btn size="small" variant="elevated" elevation="1"> See all </v-btn>
      </div>
    </v-list>
  </v-menu>
</template>

<style scoped lang="scss">
// ::v-deep .v-list-item__append,
// ::v-deep .v-list-item__prepend {
//   height: 100%;
// }
</style>
