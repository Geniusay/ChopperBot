<template>
  <v-container>
    <v-card elevation="6">
      <div
        v-masonry
        class="masonry"
        transition-duration="0.3s"
        item-selector=".item"
      >
        <div v-masonry-tile class="item" v-for="i in 50" :key="i">
          <!-- item -->
          <div class="max-w-sm rounded overflow-hidden shadow-lg m-4">
            <img
              class="w-full"
              :src="'https://picsum.photos/200/100?random=' + i"
              :lazy-src="'https://picsum.photos/200/100?random=' + i"
              alt="Sunset in the mountains"
            />
            <div class="px-6 py-4">
              <div class="font-bold text-xl mb-2">The Coldest Sunset</div>
              <p class="text-gray-700 text-base">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Voluptatibus quia, nulla! Maiores et perferendis eaque,
                exercitationem praesentium nihil.
              </p>
            </div>
            <div class="px-6 py-4">
              <span
                class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2"
                >#photography</span
              >
              <span
                class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2"
                >#travel</span
              >
              <span
                class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700"
                >#winter</span
              >
            </div>
          </div>
          <!-- END item -->
        </div>
      </div>
    </v-card>
  </v-container>
</template>
<script setup lang="ts">
import { useDisplay } from "vuetify";
const { name } = useDisplay();
const columnNumber = computed(() => {
  switch (name.value) {
    case "md":
      return 2;
    case "lg":
      return 3;
    case "xl":
      return 4;
    case "xxl":
      return 6;
  }
});
</script>
<style scoped></style>
