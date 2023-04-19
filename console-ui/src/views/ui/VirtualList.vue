<template>
  <v-container>
    <v-card class="d-flex pt-4 px-4 mb-3">
      <v-text-field
        type="number"
        v-model="index"
        placeholder="Jump to index"
        variant="outlined"
        color="primary"
      ></v-text-field>
      <v-btn
        class="ml-2"
        variant="elevated"
        color="primary"
        height="56"
        @click="handleScrollTo"
        >Go</v-btn
      >
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        type="search"
        placeholder="Filter list by size .eg small or large "
        variant="outlined"
        prepend-icon="mdi-magnify"
        color="primary"
      ></v-text-field>
    </v-card>

    <div v-bind="containerProps" style="height: 70vh; overflow: auto">
      <div v-bind="wrapperProps">
        <v-container class="">
          <v-row>
            <v-col
              cols="12"
              sm="6"
              md="4"
              xl="2"
              v-for="item in list"
              :key="item.index"
            >
              <v-card height="500" elevation="4">
                <v-img
                  class="align-end text-white"
                  :src="'https://picsum.photos/200/300?random=' + item.index"
                  :lazy-src="
                    'https://picsum.photos/200/300?random=' + item.index
                  "
                  cover
                  :aspect-ratio="2 / 3"
                >
                  <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey-lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                  <v-card-title
                    class="d-flex justify-space-between align-center"
                    >No.{{ item.index }}

                    <v-btn size="small" variant="text" icon="mdi-heart"></v-btn>
                  </v-card-title>
                </v-img>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </v-container>
</template>
<script setup lang="ts">
import { useVirtualList } from "@vueuse/core";

const index = ref(2);
const search = ref("");

// This is a function that returns an array of objects with a height and size property.
// The height property is either 42 or 84 and the size property is either "small" or "large"

const allItems = Array.from(Array(9999).keys()).map((i) => ({
  // generate array of objects with height and size properties
  height: i % 2 === 0 ? 42 : 84, // if index is even, height is 42, otherwise 84
  size: i % 2 === 0 ? "small" : "large", // if index is even, size is small, otherwise large
}));

const filteredItems = computed(() => {
  return allItems.filter((item) =>
    item.size.startsWith(search.value.toLowerCase())
  );
});

const { list, containerProps, wrapperProps, scrollTo } = useVirtualList(
  filteredItems,
  {
    itemHeight: (i) => filteredItems.value[i].height + 8,
    overscan: 10,
  }
);

const handleScrollTo = () => {
  scrollTo(index.value);
};
</script>
<style scoped>
.v-card-title {
  background-color: rgba(0, 0, 0, 0.5);
}
</style>
