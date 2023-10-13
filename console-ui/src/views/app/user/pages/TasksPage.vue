<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script lang="ts" setup>
import UserList from "@/views/app/user/component/UserList.vue";
import { useUserStore } from "@/views/app/user/userStore";
const userStore = useUserStore();
</script>

<template>
  <UserList :tasks="userStore.getUserList" />
</template>

<style lang="scss" scoped></style>
