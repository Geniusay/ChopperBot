<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useRouter } from "vue-router";
const router = useRouter();
const fn = () => {
  console.log(router);
};

const colors = ref([
  "primary",
  "secondary",
  "success",
  "info",
  "warning",
  "error",
  "purple",
  "pink",
  "orange",
  "teal",
  "green",
  "blue",
  "indigo",
  "deep-purple",
  "deep-orange",
  "cyan",
  "light-green",
  "light-blue",
  "lime",
  "amber",
  "brown",
  "grey",
  "blue-grey",
  "black",
  "white",
]);

const currentColor = ref("primary");

const user = reactive({
  name: "John",
  age: 30,
  address: {
    street: "123 Main St",
    city: "Anytown",
    state: "CA",
    zip: "12345",
  },
});
</script>

<template>
  <div class="d-flex flex-wrap">
    <v-card
      class="ma-5"
      width="200"
      height="200"
      v-for="i in 24"
      :elevation="i"
    >
    </v-card>
  </div>
  <v-card>
    <v-btn color="success">text</v-btn>
    <v-btn color="error">text</v-btn>
    <v-btn color="info">text</v-btn>
    <v-btn color="teal">text</v-btn>
    <v-btn color="blue">text</v-btn>
    <v-btn color="#003">text</v-btn>
    <v-chip color="success">text</v-chip>
    <v-chip color="error">text</v-chip>
    <v-chip color="info">text</v-chip>
    <v-chip color="teal">text</v-chip>
    <v-chip color="blue">text</v-chip>
    <v-chip color="#003">text</v-chip>

    <v-select
      class="ma-5"
      :items="colors"
      v-model="currentColor"
      :color="currentColor"
      label="color"
      hide-details
    ></v-select>

    <v-select
      density="compact"
      class="ma-5"
      :items="colors"
      v-model="currentColor"
      :color="currentColor"
      variant="outlined"
      hide-details
    ></v-select>

    <v-select
      density="compact"
      class="ma-5"
      :items="colors"
      v-model="currentColor"
      :color="currentColor"
      variant="underlined"
      hide-details
    ></v-select>

    <v-select
      label="Select"
      color="error"
      class="ma-5"
      :items="[
        'California',
        'Colorado',
        'Florida',
        'Georgia',
        'Texas',
        'Wyoming',
      ]"
      variant="solo"
    ></v-select>
    <v-card class="pa-5 ma-5">
      <v-text-field
        color="blue"
        v-model="user.name"
        label="name"
      ></v-text-field>
      <v-text-field color="blue" v-model="user.age" label="age"></v-text-field>
      <v-text-field
        color="blue"
        v-model="user.address.street"
        label="street"
      ></v-text-field>
      <v-text-field
        color="blue"
        v-model="user.address.city"
        label="city"
      ></v-text-field>
      <v-text-field
        color="blue"
        v-model="user.address.state"
        label="state"
      ></v-text-field>
      <v-text-field
        color="blue"
        v-model="user.address.zip"
        label="zip"
      ></v-text-field>
    </v-card>
    <v-sheet class="ma-5" height="200"> </v-sheet>
    <v-card class="pa-5 ma-5">
      <div>name:{{ user.name }}</div>
      <div>age:{{ user.age }}</div>
      <div>street:{{ user.address.street }}</div>
      <div>city:{{ user.address.city }}</div>
      <div>state:{{ user.address.state }}</div>
      <div>zip:{{ user.address.zip }}</div>
    </v-card>

    <v-card class="ma-5" variant="outlined">
      <v-card-item class="py-4 px-6">
        <v-card-title class="text-h5">Title</v-card-title>
      </v-card-item>
      <v-divider />

      <v-card-text> Asdas </v-card-text>
    </v-card>

    <v-card>
      <v-btn color="success" @click="fn">text</v-btn>
    </v-card>
  </v-card>
</template>

<style scoped lang="scss"></style>
