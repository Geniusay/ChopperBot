<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { searchUsersApi } from "@/api/unsplashApi";
import CopyLabel from "@/components/common/CopyLabel.vue";

const loading = ref(true);
const totalRows = ref(0);

const queryOptions = reactive({
  query: "cat",
  page: 1,
  per_page: 25,
});

const headers = [
  { title: "用户名", key: "username" },
  { title: "头像", key: "avatar" },
  { title: "用户id", key: "id" },
  { title: "全名", key: "name" },
  { title: "位置", key: "location", width: "200px" },
  { title: "是否可用", key: "for_hire", align: "center" },
  { title: "收藏数", key: "total_collections" },
  { title: "喜欢数", key: "total_likes" },
  { title: "照片数", key: "total_photos" },
  { title: "接受条款", key: "accepted_tos", align: "center" },
  { title: "作品集", key: "portfolio_url" },
];

const usersList = ref([]);

const getUsers = async () => {
  loading.value = true;
  const params = queryOptions;
  const usersResponse = await searchUsersApi(params);

  usersList.value = usersResponse.data.results.map((user) => {
    return {
      id: user.id,
      avatar: user.profile_image.small,
      username: user.username,
      name: user.name,
      location: user.location,
      for_hire: user.for_hire,
      total_collections: user.total_collections,
      total_likes: user.total_likes,
      total_photos: user.total_photos,
      accepted_tos: user.accepted_tos,
      portfolio_url: user.portfolio_url,
    };
  });

  totalRows.value = usersResponse.data.total;
  loading.value = false;
};

const onUpdateOptions = async (options) => {
  queryOptions.per_page = options.itemsPerPage;
  queryOptions.page = options.page;
  await getUsers();
};

const getLikesColor = (likes) => {
  if (likes > 400) return "red";
  else if (likes > 200) return "orange";
  else return "grey";
};
</script>

<template>
  <div class="">
    <v-card>
      <v-card-title class="font-weight-bold">
        <span> Unsplash Users</span>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="queryOptions.query"
          variant="solo"
          class="elevation-1"
          append-icon="mdi-magnify"
          @click:append="getUsers"
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
          :items="usersList"
          :search="queryOptions.query"
          :loading="loading"
          :items-per-page="queryOptions.per_page"
          :items-length="totalRows"
          item-value="id"
          @update:options="onUpdateOptions"
        >
          <template v-slot:item="{ item }">
            <tr>
              <td class="font-weight-bold">
                <CopyLabel :text="item.columns.username" />
              </td>
              <td>
                <v-avatar size="30">
                  <img :src="item.columns.avatar" alt="alt" />
                </v-avatar>
              </td>
              <td>{{ item.columns.id }}</td>

              <td>{{ item.columns.name }}</td>
              <td>{{ item.columns.location }}</td>

              <td class="text-center">
                <v-chip
                  size="small"
                  :color="item.columns.for_hire ? 'blue' : 'grey'"
                  class="font-weight-bold"
                >
                  {{ item.columns.for_hire ? "Hire" : "No Hire" }}</v-chip
                >
              </td>
              <td>
                {{ item.columns.total_collections }}
              </td>
              <td>
                <v-chip
                  size="small"
                  :color="getLikesColor(item.columns.total_likes)"
                  class="font-weight-bold"
                >
                  {{ item.columns.total_likes }}</v-chip
                >
              </td>
              <td>
                {{ item.columns.total_photos }}
              </td>
              <td class="text-center">
                <v-chip
                  size="small"
                  :color="item.columns.accepted_tos ? 'green' : 'pink'"
                  class="font-weight-bold"
                >
                  <v-icon
                    start
                    :icon="
                      item.columns.accepted_tos
                        ? 'mdi-security '
                        : 'mdi-close-octagon'
                    "
                  ></v-icon>
                  {{
                    item.columns.accepted_tos ? "Accepted" : "Not Accepted"
                  }}</v-chip
                >
              </td>
              <td>
                <a :href="item.columns.portfolio_url" target="_blank">
                  {{ item.columns.portfolio_url }}
                </a>
              </td>
            </tr>
          </template>
        </v-data-table-server>
      </v-card-text>
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
