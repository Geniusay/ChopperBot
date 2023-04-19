<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { searchCollectionsApi } from "@/api/unsplashApi";
import CopyLabel from "@/components/common/CopyLabel.vue";
import moment from "moment";

const loading = ref(true);
const totalRows = ref(0);

const queryOptions = reactive({
  query: "cat",
  page: 1,
  per_page: 10,
});

const headers = [
  { title: "ID", key: "id" },
  { title: "标题", key: "title" },
  { title: "拥有者", key: "user" },
  { title: "照片数量", key: "total_photos", align: "center" },

  { title: "封面图", key: "cover_photo" },
  { title: "预览图", key: "preview_photos" },
  { title: "链接", key: "links" },
  { title: "标签", key: "tags" },
  { title: "发布时间", key: "published_at" },
];

const collectionList = ref([]);

const getCollections = async () => {
  loading.value = true;
  const params = queryOptions;
  const collectionsResponse = await searchCollectionsApi(params);

  collectionList.value = collectionsResponse.data.results.map((collection) => {
    return {
      id: collection.id,
      title: collection.title,
      user: collection.user,
      total_photos: collection.total_photos,

      cover_photo: collection.cover_photo,
      preview_photos: collection.preview_photos,
      links: collection.links,
      tags: collection.tags,
      published_at: moment(collection.published_at).format("YYYY/MM/DD"),
    };
  });

  totalRows.value = collectionsResponse.data.total;
  loading.value = false;
};

const onUpdateOptions = async (options) => {
  queryOptions.per_page = options.itemsPerPage;
  queryOptions.page = options.page;
  await getCollections();
};
</script>

<template>
  <div class="">
    <v-card>
      <v-card-title class="font-weight-bold">
        <span> Unsplash Collections</span>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="queryOptions.query"
          variant="solo"
          class="elevation-1"
          append-icon="mdi-magnify"
          @click:append="getCollections"
          label="Search"
          single-line
          hide-details
          clearable
        ></v-text-field>
      </v-card-title>
      <hr />
      <v-card-text>
        <v-data-table-server
          :headers="headers"
          :items="collectionList"
          :search="queryOptions.query"
          :loading="loading"
          :items-per-page="queryOptions.per_page"
          :items-length="totalRows"
          item-value="id"
          @update:options="onUpdateOptions"
          fixed-header
          height="900"
        >
          <template v-slot:item="{ item }">
            <tr>
              <td>{{ item.columns.id }}</td>
              <td>{{ item.columns.title }}</td>
              <td class="font-weight-bold">
                <v-avatar size="30" class="mr-2">
                  <img :src="item.columns.user.profile_image.small" alt="alt" />
                </v-avatar>
                <CopyLabel :text="item.columns.user.username" />
              </td>

              <td class="text-center">
                <v-chip size="small">
                  {{ item.columns.total_photos }}
                </v-chip>
              </td>

              <td class="pa-2">
                <v-img
                  :src="item.columns.cover_photo.urls.thumb"
                  max-width="100px"
                />
              </td>
              <td>
                <!-- <v-img
                  v-for="photo in item.columns.preview_photos"
                  :key="photo.id"
                  :src="photo.urls.thumb"
                  max-width="100px"
                  class="mr-2"
                /> -->
              </td>
              <td>
                <CopyLabel :text="item.columns.links.html" />
              </td>

              <td>
                <v-chip
                  v-for="tag in item.columns.tags"
                  variant="outlined"
                  color="primary"
                  size="small"
                  class="font-weight-bold mr-1"
                >
                  {{ tag.title }}
                </v-chip>
              </td>
              <td>
                {{ item.columns.published_at }}
              </td>
            </tr>
          </template>

          <template v-slot:expanded-row="{ columns }">
            <tr>
              <td :colspan="columns.length">More info about</td>
            </tr>
          </template>
        </v-data-table-server>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
