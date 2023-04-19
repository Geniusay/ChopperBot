<script setup lang="ts">
import { useTodoStore } from "@/views/app/todo/todoStore";
const todoStore = useTodoStore();

const loading = ref(true);
onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});

const searchKey = ref("");
const filterdTodoList = computed(() => {
  return todoStore.getTodoList.filter((todo) => {
    return todo.title.toLowerCase().includes(searchKey.value.toLowerCase());
  });
});

const getLabelColor = (id: string) => {
  // Find the label by id from the labels array
  const label = todoStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.color : "";
};
</script>
<template>
  <!-- loading spinner -->
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular indeterminate color="primary"></v-progress-circular>
  </div>
  <div v-else>
    <v-text-field
      clearable
      variant="solo"
      class="elevation-1 ma-3"
      hide-details
      prepend-inner-icon="mdi-magnify"
      placeholder="Filter Tasks"
      v-model="searchKey"
    ></v-text-field>

    <perfect-scrollbar class="todo-list">
      <transition-group name="fade">
        <div v-for="todo in filterdTodoList" :key="todo.id">
          <div class="todo-item d-flex align-center pa-5">
            <v-checkbox-btn
              v-model="todo.completed"
              color="primary"
              class="pe-2"
            ></v-checkbox-btn>
            <v-avatar size="40">
              <v-img
                src="https://avatars.githubusercontent.com/u/35951244?v=4"
                alt="alt"
              />
            </v-avatar>
            <div class="flex-1 mx-5">
              <div
                class="font-weight-bold"
                :class="todo.completed ? 'text-decoration-line-through' : ''"
              >
                {{ todo.title }}
              </div>
              <div
                :class="todo.completed ? 'text-decoration-line-through' : ''"
              >
                {{ todo.detail }}
              </div>
              <div>
                <v-chip
                  size="x-small"
                  variant="outlined"
                  class="mr-1 mt-1"
                  :color="getLabelColor(tag)"
                  v-for="tag in todo.tags"
                >
                  {{ tag }}
                </v-chip>
              </div>
            </div>
            <v-btn
              icon="mdi-delete-outline"
              variant="text"
              @click="todoStore.deleteTodoById(todo.id)"
            ></v-btn>
          </div>
        </div>
      </transition-group>
    </perfect-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.todo-list {
  max-height: 400px;
  overflow: scroll;
  .todo-item {
    transition: all 0.3s;
    &:hover {
      transition: all 0.3s;
      background-color: rgba(99, 99, 99, 0.2);
      box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
      cursor: pointer;
    }
  }
}
</style>
