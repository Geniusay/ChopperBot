<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
const props = defineProps<{
  title: string;
}>();

const show = ref(true);
</script>

<template>
  <v-card class="mb-5">
    <v-card-title>
      <span> {{ props.title }}</span>
      <v-spacer></v-spacer>
      <v-btn v-if="show" icon variant="flat" @click="show = false">
        <v-icon>mdi-menu-up</v-icon>
      </v-btn>
      <v-btn v-else icon variant="flat" @click="show = true">
        <v-icon>mdi-menu-down</v-icon>
      </v-btn>
    </v-card-title>

    <div v-if="show">
      <v-divider></v-divider>
      <v-card-text>
        <slot></slot>
      </v-card-text>
    </div>
  </v-card>
</template>

<style scoped lang="scss"></style>
