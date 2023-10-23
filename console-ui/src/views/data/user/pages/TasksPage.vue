<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script lang="ts" setup>
import UserList from "@/views/data/user/component/UserList.vue";
import { useUserStore } from "@/views/data/user/userStore";
const userStore = useUserStore();
</script>

<template>
  <UserList :tasks="userStore.getUserList" />
</template>

<style lang="scss" scoped></style>
