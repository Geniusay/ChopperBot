<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { searchPhotosApi } from "@/api/unsplashApi";
import { Icon } from "@iconify/vue";
import { useSnackbarStore } from "@/stores/snackbarStore";
import CopyLabel from "@/components/common/CopyLabel.vue";
const snackbarStore = useSnackbarStore();

const queryOptions = reactive({
  query: "vue",
  page: 1,
  per_page: 25,
});
const photosList = ref<any>([]);
const hasMore = ref(true);
const loading = ref(false);

const loadMore = async () => {
  loading.value = true;
  console.log(hasMore.value);

  if (!hasMore.value) {
    loading.value = false;
    snackbarStore.showMessage("没有更多了");
    return;
  }

  const photosResponse = await searchPhotosApi(queryOptions);
  const newList = photosResponse.data.results;
  photosList.value = [...photosList.value, ...newList];
  queryOptions.page += 1;
  hasMore.value = newList.length > 0;
  loading.value = false;
};

onMounted(() => {
  loadMore();
});

const scrollBottom = ref(0);

const onScroll = (e) => {
  const target = e.target;
  scrollBottom.value =
    target.scrollHeight - target.scrollTop - target.clientHeight;
  if (scrollBottom.value < 100) {
    loadMore();
  }
};
</script>

<template>
  {{ scrollBottom }}
  <perfect-scrollbar class="photo-container" v-scroll.self="onScroll">
    <v-table>
      <thead>
        <tr>
          <th class="text-left">size</th>
          <th class="text-left">color</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="photo in photosList" :key="photo.id">
          <td>{{ photo.id }}</td>

          <td>
            <v-chip size="small" :color="photo.color">
              <CopyLabel :text="photo.color" />
            </v-chip>
          </td>
        </tr>
      </tbody>
    </v-table>
    <div v-if="hasMore" class="d-flex justify-center py-5">
      <Icon color="#705CF6" icon="eos-icons:bubble-loading" />
    </div>
  </perfect-scrollbar>
</template>

<style scoped lang="scss">
.photo-container {
  height: 600px;
}
</style>
