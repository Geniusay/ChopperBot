<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
interface UserStatus {
  code: string;
  name: string;
  label: string;
  color: string;
}

const userStatusList = [
  {
    code: "online",
    name: "us",
    label: "Online",
    color: "success",
  },
  {
    code: "away",
    name: "cn",
    label: "Away",
    color: "warning",
  },
  {
    code: "busy",
    name: "jp",
    label: "Busy",
    color: "error",
  },
  {
    code: "offline",
    name: "kr",
    label: "Offline",
    color: "grey",
  },
];

const currentStatus = ref<UserStatus>({
  code: "online",
  name: "us",
  label: "Online",
  color: "success",
});

const setStatus = (status: string) => {
  currentStatus.value = userStatusList.find(
    (userStatus) => userStatus.code === status
  ) as UserStatus;
};
</script>

<template>
  <v-menu scroll-y :close-on-content-click="false">
    <template v-slot:activator="{ props }">
      <v-btn
        width="60"
        variant="text"
        size="small"
        v-bind="props"
        :color="currentStatus.color"
      >
        {{ currentStatus.label }}
      </v-btn>
    </template>
    <v-list elevation="1">
      <v-list-item
        v-for="status in userStatusList"
        :key="status.code"
        @click="setStatus(status.code)"
        density="compact"
      >
        <v-list-item-title class="text-body-2">
          <v-icon size="small" :color="status.color">mdi-circle-medium</v-icon>
          {{ status.label }}</v-list-item-title
        >
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<style scoped lang="scss"></style>
