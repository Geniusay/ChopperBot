<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { Todo } from "../todoTypes";
import { useTodoStore } from "../todoStore";
const todoStore = useTodoStore();
const dialog = ref(false);
const task = ref<Todo>({
  id: "",
  title: "",
  detail: "",
  tags: [],
  completed: false,
});
const isEdit = computed(() => task.value && !!task.value.id);

const close = () => {
  dialog.value = false;
};

const save = () => {
  if (task.value) {
    if (isEdit.value) {
      todoStore.updateTodo(task.value);
    } else {
      todoStore.addNewTodo(task.value);
    }
  }
  close();
};
</script>

<template>
  <v-dialog v-model="dialog" width="600">
    <v-card>
      <v-card-title class="pa-2">
        {{ isEdit ? "Edit Task" : "Add Task" }}
        <v-spacer></v-spacer>
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-card-title>

      <v-divider></v-divider>

      <!-- task form -->
      <div>
        <v-text-field
          v-model="task.title"
          class="px-2 py-1"
          solo
          flat
          placeholder="Title"
          autofocus
          hide-details
          @keyup.enter="save"
        ></v-text-field>

        <v-divider></v-divider>

        <v-textarea
          v-model="task.detail"
          class="px-2 py-1"
          variant="solo"
          placeholder="Description"
          hide-details
        ></v-textarea>

        <v-select
          v-model="task.tags"
          class="px-2 my-3"
          :items="todoStore.labels"
          placeholder="Labels"
          item-value="id"
          hide-selected
          hide-details
          multiple
        >
          <template v-slot:selection="{ attrs, item, parent, selected }">
            <v-chip
              v-if="item === Object(item)"
              v-bind="attrs"
              class="font-weight-bold"
              :color="item.color"
              variant="outlined"
              :input-value="selected"
              label
            >
              <span class="pr-2">
                {{ item.title }}
              </span>
              <v-icon size="small" @click="parent.selectItem(item)"
                >close</v-icon
              >
            </v-chip>
          </template>

          <template v-slot:item="{ item }">
            <v-chip :color="item.color" label variant="outlined" size="small">
              {{ item.title }}
            </v-chip>
          </template>
        </v-select>
      </div>

      <v-divider></v-divider>

      <v-card-actions class="pa-2">
        <v-btn variant="outlined" @click="close">Close</v-btn>
        <v-spacer></v-spacer>
        <v-btn color="primary" @click="save">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped lang="scss"></style>
