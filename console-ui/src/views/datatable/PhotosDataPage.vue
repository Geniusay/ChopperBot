<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { searchPhotosApi } from "@/api/unsplashApi";
import CopyLabel from "@/components/common/CopyLabel.vue";
import moment from "moment";
import { useSnackbarStore } from "@/stores/snackbarStore";
const snackbarStore = useSnackbarStore();

const loading = ref(true);
const totalRows = ref(0);

const queryOptions = reactive({
  query: "cat",
  page: 1,
  per_page: 25,
});

const headers = [
  { title: "ID", key: "id" },
  { title: "拥有者", key: "user" },
  { title: "颜色", key: "color", align: "center" },
  { title: "尺寸", key: "size", align: "center" },
  { title: "描述", key: "alt_description" },
  { title: "缩略图", key: "thumb" },
  { title: "下载", key: "download" },
  { title: "喜欢", key: "likes" },
  { title: "标签", key: "tags" },
  { title: "创建时间", key: "created_at" },
];

const photosList = ref([]);

const getPhotos = async () => {
  loading.value = true;
  const params = queryOptions;
  const photosResponse = await searchPhotosApi(params);

  photosList.value = photosResponse.data.results.map((photo) => {
    return {
      id: photo.id,
      // avatar: photo.user.profile_image.small,
      user: photo.user,
      color: photo.color,
      size: photo.width + " x " + photo.height,
      alt_description: photo.alt_description,
      thumb: photo.urls.thumb,
      download: photo.links.download,
      likes: photo.likes,
      tags: photo.tags,
      created_at: moment(photo.created_at).format("YYYY/MM/DD"),
    };
  });

  totalRows.value = photosResponse.data.total;
  loading.value = false;
};

const onUpdateOptions = async (options) => {
  queryOptions.per_page = options.itemsPerPage;
  queryOptions.page = options.page;
  await getPhotos();
};

const getLikesColor = (likes) => {
  if (likes > 400) return "red";
  else if (likes > 200) return "orange";
  else return "grey";
};

const downloadPhoto = (photo) => {
  const a = document.createElement("a");
  a.href = photo.download + "&force=true";
  a.download = photo.id + ".jpg";
  a.click();
};
</script>

<template>
  <div class="">
    <v-card>
      <v-card-title class="font-weight-bold">
        <span> Unsplash Photos</span>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="queryOptions.query"
          variant="solo"
          class="elevation-1"
          append-icon="mdi-magnify"
          @click:append="getPhotos"
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
          :items="photosList"
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
              <td class="font-weight-bold">
                <v-avatar size="30" class="mr-2">
                  <img :src="item.columns.user.profile_image.small" alt="alt" />
                </v-avatar>
                <CopyLabel :text="item.columns.user.username" />
              </td>

              <td class="text-center">
                <v-chip size="small" :color="item.columns.color">
                  <CopyLabel :text="item.columns.color" />
                </v-chip>
              </td>
              <td class="text-center">{{ item.columns.size }}</td>

              <td>{{ item.columns.alt_description }}</td>
              <td class="pa-2">
                <v-img :src="item.columns.thumb" max-width="100px" />
              </td>

              <td>
                <v-btn icon @click="downloadPhoto(item.columns)">
                  <v-icon>mdi-download</v-icon>
                </v-btn>
              </td>
              <td>
                <v-chip
                  size="small"
                  :color="getLikesColor(item.columns.likes)"
                  class="font-weight-bold"
                >
                  {{ item.columns.likes }}</v-chip
                >
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
                {{ item.columns.created_at }}
              </td>
            </tr>
          </template>
        </v-data-table-server>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
