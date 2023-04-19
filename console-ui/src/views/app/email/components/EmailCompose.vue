<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
const dialog = ref(false);
</script>

<template>
  <v-dialog v-model="dialog" width="600" location="bottom">
    <template v-slot:activator="{ props }">
      <v-btn
        color="#705CF6"
        block
        :props="props"
        size="large"
        class="mb-3 text-white"
        @click="dialog = true"
        >Write an email</v-btn
      >
    </template>
    <v-card>
      <v-card-title> 请输入您的API KEY </v-card-title>
      <hr />
      <v-card-text> </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn block color="primary" text @click="dialog = false">关闭</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped lang="scss"></style>
