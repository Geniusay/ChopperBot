<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script lang="ts" setup>
import UserList from "@/views/app/user/component/UserList.vue";
import { useUserStore } from "@/views/app/user/userStore";

const userStore = useUserStore();
const route = useRoute();

watch(
  () => route.params.id,
  (id) => {
    userStore.currentLabel = id as string;
  }
);
</script>

<template>
  <UserList :tasks="userStore.getLabelUsers" />
</template>

<style lang="scss" scoped></style>
