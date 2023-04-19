<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { getTopicsApi } from "@/api/unsplashApi";
import CopyLabel from "@/components/common/CopyLabel.vue";
import moment from "moment";

const loading = ref(true);
const totalRows = ref(0);

const queryOptions = reactive({
  query: "cat",
  page: 1,
  per_page: 19,
});

const headers = [
  { title: "ID", key: "id" },
  { title: "标题", key: "title" },
  { title: "封面图", key: "cover_photo" },
  { title: "照片数量", key: "total_photos", align: "center" },
  { title: "描述", key: "description", width: "500px" },
  { title: "预览图", key: "preview_photos" },
  { title: "链接", key: "links" },
  { title: "发布时间", key: "published_at" },
  { title: "", key: "data-table-expand" },
];

const topicList = ref([]);

const getTopics = async () => {
  loading.value = true;
  const params = queryOptions;
  const topicsResponse = await getTopicsApi(params);

  topicList.value = topicsResponse.data.map((topic) => {
    return {
      id: topic.id,
      title: topic.title,
      description: topic.description,
      total_photos: topic.total_photos,
      cover_photo: topic.cover_photo,
      preview_photos: topic.preview_photos,
      links: topic.links,
      published_at: moment(topic.published_at).format("YYYY/MM/DD"),
    };
  });

  totalRows.value = topicsResponse.data.length;
  loading.value = false;
};

const onUpdateOptions = async (options) => {
  queryOptions.per_page = options.itemsPerPage;
  queryOptions.page = options.page;
  await getTopics();
};
</script>

<template>
  <div class="">
    <v-card>
      <v-card-title class="font-weight-bold">
        <span> Unsplash Topics</span>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="queryOptions.query"
          variant="solo"
          class="elevation-1"
          append-icon="mdi-magnify"
          @click:append="getTopics"
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
          :items="topicList"
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
              <td class="pa-2">
                <v-img
                  :src="item.columns.cover_photo.urls.thumb"
                  max-width="100px"
                />
              </td>
              <td class="text-center">
                <v-chip size="small">
                  {{ item.columns.total_photos }}
                </v-chip>
              </td>
              <td>{{ item.columns.description }}</td>

              <td>
                <CopyLabel :text="item.columns.links.html" />
              </td>
              <td>
                {{ item.columns.published_at }}
              </td>
            </tr>
          </template>
        </v-data-table-server>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
