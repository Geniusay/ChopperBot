<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import EmailList from "../components/EmailList.vue";
import { useEmailStore } from "../emailStore";
const emailStore = useEmailStore();
</script>

<template>
  <EmailList :emails="emailStore.getInboxList" />
</template>

<style scoped lang="scss"></style>
