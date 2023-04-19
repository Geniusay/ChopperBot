<script setup lang="ts">
import { faker } from "@faker-js/faker";
const generateMessage = () => {
  return {
    avatar: faker.internet.avatar(),
    message: faker.lorem.text(),
    username: faker.internet.userName(),
    color: faker.internet.color(),
    time: faker.date.past(),
    title: faker.lorem.sentence(),
  };
};

const list = ref(
  Array.from({ length: 5000 }, (value, index) => ({
    id: index + "",
    ...generateMessage(),
  }))
);

const length = computed(() => list.value.length);
</script>
<template>
  <v-container>
    <v-card height="1000" elevation="6">
      <v-list elevation="1" density="compact">
        <v-list-subheader>Total {{ length }} users</v-list-subheader>
        <RecycleScroller
          class="scroller"
          :items="list"
          :item-size="50"
          key-field="id"
          v-slot="{ item }"
        >
          <v-list-item @click="" three-line>
            <!-- ---------------------------------------------- -->
            <!-- Prepend-->
            <!-- ---------------------------------------------- -->
            <template v-slot:prepend>
              <span class="mr-3">{{ item.id }}</span>
              <v-avatar size="40" :color="item.color">
                <img :src="item.avatar" />
              </v-avatar>
            </template>
            <!-- ---------------------------------------------- -->
            <!-- Append-->
            <!-- ---------------------------------------------- -->
            <template v-slot:append>
              <div class="full-h d-flex align-center">
                <span class="text-body-2 text-grey"> {{ item.past }}</span>
              </div>
            </template>
            <!-- ---------------------------------------------- -->
            <!-- Main Content-->
            <!-- ---------------------------------------------- -->
            <div>
              <v-list-item-title class="font-weight-bold text-primary">{{
                item.username
              }}</v-list-item-title>
              <v-list-item-subtitle>{{ item.title }}</v-list-item-subtitle>
            </div>
          </v-list-item>
        </RecycleScroller>
      </v-list>
    </v-card>
  </v-container>
</template>

<style scoped>
.scroller {
  height: 1000px;
}
</style>
