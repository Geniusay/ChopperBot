<template>
  <v-container>
    <v-card
      v-if="isLoading"
      class="d-flex align-center justify-center"
      color="primary"
      min-height="1000"
    >
      <Loading />
    </v-card>
    <v-card v-else>
      <masonry-wall :items="filteredItems" :ssr-columns="1" :padding="30">
        <template #default="{ item }">
          <v-card class="ma-3">
            <v-img
              class="align-end text-white"
              :src="item.download_url"
              :lazy-src="item.download_url"
              cover
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular
                    indeterminate
                    color="grey-lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
              <v-card-title>Title{{ item.author }}</v-card-title>
            </v-img>

            <v-card-subtitle class="pt-4">
              The {{ item.id }} item
            </v-card-subtitle>

            <v-card-text>
              <div>height:{{ item.height }} width:{{ item.width }}</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary"> Share </v-btn>

              <v-btn color="primary"> Explore </v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </masonry-wall>
    </v-card>
    <v-divider class="my-5"></v-divider>
  </v-container>
</template>
<script setup lang="ts">
import { useAxios } from "@vueuse/integrations/useAxios";
import Loading from "@/components/loading/Loading01.vue";

const { data, isLoading } = useAxios(
  "https://picsum.photos/v2/list?page=2&limit=20"
);

const filteredItems: any = computed(() => {
  return data;
});
</script>
<style scoped></style>
