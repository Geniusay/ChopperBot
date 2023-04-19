<template>
  <v-card @click="$emit('edit')" class="pa-5 mt-4 card-shadow">
    <div class="d-flex align-start font-weight-bold text-title">
      <span class="flex-1">{{ card.title }}</span>
      <v-menu location="bottom end" transition="slide-x-transition">
        <template v-slot:activator="{ props }">
          <v-btn
            v-bind="props"
            size="small"
            variant="text"
            icon="mdi-dots-vertical"
            rounded
            color="primary"
            class="my-n2"
          ></v-btn>
        </template>
        <v-list density="compact">
          <v-list-item @click="$emit('edit')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                icon="flat-color-icons:edit-image"
                :rotate="2"
                :horizontalFlip="true"
                :verticalFlip="true"
                class="mr-1"
              />
              <span> Edit</span>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click="$emit('delete')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                icon="flat-color-icons:full-trash"
                :rotate="2"
                :horizontalFlip="true"
                :verticalFlip="true"
                :inline="true"
                class="mr-1"
              />
              Delete</v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <div class="text-content">{{ props.card.description }}</div>
  </v-card>
</template>
<script setup lang="ts">
import { Icon } from "@iconify/vue";

const props = defineProps({
  // Card content to display
  card: {
    type: Object,
    default: () => ({}),
  },
});
</script>

<style lang="scss" scoped>
.card-shadow {
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
}
</style>
