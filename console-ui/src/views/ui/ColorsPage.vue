<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { fullColorList } from "@/data/colors";

const searchKey = ref("");

const computedColors: any = computed(() => {
  return fullColorList.filter((colorTheme) => {
    return colorTheme.key.toLowerCase().includes(searchKey.value.toLowerCase());
  });
});
</script>

<template>
  <div class="">
    <div>
      <!-- ---------------------------------------------- -->
      <!-- Filter Input -->
      <!-- ---------------------------------------------- -->
      <v-text-field
        clearable
        variant="solo"
        class="elevation-1 ma-3"
        hide-details
        prepend-inner-icon="mdi-magnify"
        append-inner-icon="mdi-palette-outline"
        placeholder="Search Colors"
        v-model="searchKey"
      ></v-text-field>

      <v-container>
        <v-row>
          <v-col
            cols="12"
            sm="6"
            md="4"
            lg="3"
            v-for="colorTheme in computedColors"
            :key="colorTheme.key"
          >
            <v-card elevation="1" class="mb-10">
              <v-card :color="colorTheme.class" height="200">
                <h1 class="text-h6 font-weight-bold pa-5">
                  {{ colorTheme.key }}
                </h1>
              </v-card>
              <v-card
                :color="
                  color.key === 'base' ? colorTheme.class : color.colorClass
                "
                height="100"
                class="mt-5 pa-5"
                v-for="color in colorTheme.colors"
                :key="color.key"
              >
                <h3>
                  {{
                    color.key === "base" ? colorTheme.class : color.colorClass
                  }}
                </h3>
                <h3 class="mt-2">
                  {{ color.color }}
                </h3>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<style scoped lang="scss"></style>
