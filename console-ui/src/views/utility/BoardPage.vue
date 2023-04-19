<template>
  <!-- board column -->
  <v-row style="min-width: 800px">
    <v-col
      cols="3"
      v-for="column in columns"
      :key="column.key"
      class="pa-4 flex-1"
    >
      <div class="d-flex align-center">
        <h5 class="font-weight-bold">{{ column.key }}</h5>
        <v-spacer></v-spacer>
        <!-- add new card button -->
        <v-btn
          variant="text"
          rounded
          icon="mdi-plus"
          size="small"
          color="primary"
          class="mr-n3"
          @click="column.isAddVisible = !column.isAddVisible"
        >
        </v-btn>
      </div>

      <!-- add new card form -->
      <v-card v-show="column.isAddVisible" class="pa-5 my-2">
        <v-text-field
          color="primary"
          v-model="column.addTitle"
          label="Title"
          variant="underlined"
          hideDetails
          placeholder="Input title for this card"
          autofocus
          @keyup.enter="addCard(column)"
          @keyup.esc="column.isAddVisible = false"
        ></v-text-field>
        <div class="mt-3 d-flex flex-md-row flex-column">
          <v-btn
            class="flex-1 ma-1"
            size="small"
            @click="column.isAddVisible = !column.isAddVisible"
            >Cancel</v-btn
          >
          <v-btn
            class="flex-1 ma-1"
            size="small"
            color="primary"
            @click="addCard(column)"
            >Add</v-btn
          >
        </div>
      </v-card>

      <!-- draggable cards -->
      <vue-draggable
        v-model="column.cards"
        v-bind="dragOptions"
        class="list-group"
        @change="column.callback"
        itemKey="id"
      >
        <template #item="{ element, index }">
          <board-card
            :key="index"
            :card="element"
            class="board-item my-2 pa-2"
            @edit="showEdit(element)"
            @delete="showDelete(element)"
          />
        </template>
      </vue-draggable>
    </v-col>
  </v-row>
  <!-- edit card dialog -->
  <v-dialog persistent v-model="editDialog" width="600">
    <v-card>
      <v-card-title class="pa-4 d-flex align-center">
        <span class="flex-1">Edit Card</span>
        <v-btn
          variant="text"
          rounded
          icon="mdi-close"
          size="small"
          color="primary"
          class="mr-n3"
          @click="editDialog = false"
        >
        </v-btn>
      </v-card-title>
      <v-divider></v-divider>
      <div class="pa-4">
        <v-text-field
          class="py-2 px-1"
          color="primary"
          v-model="title"
          label="Title"
          variant="plain"
          hideDetails
          placeholder="Title"
          autofocus
          @keyup.enter="saveCard"
        ></v-text-field>
        <v-divider></v-divider>
        <v-textarea
          v-model="description"
          class="px-2 py-1"
          variant="plain"
          placeholder="Description"
          hide-details
        ></v-textarea>
      </div>
      <v-divider></v-divider>
      <v-card-actions class="pa-4">
        <v-btn variant="outlined" @click="editDialog = false">Cancel</v-btn>
        <v-spacer></v-spacer>
        <v-btn variant="flat" color="primary" @click="saveCard">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <!-- delete card dialog -->
  <v-dialog v-model="deleteDialog" max-width="300">
    <v-card>
      <v-card-title class="text-headline">Delete</v-card-title>
      <v-card-text>DeleteDescription</v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn variant="plain" color="primary" @click="deleteDialog = false"
          >Cancel</v-btn
        >
        <v-btn variant="flat" color="error" @click="deleteCard()">Delete</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script setup lang="ts">
import VueDraggable from "vuedraggable";
import BoardCard from "@/components/BoardCard.vue";

const list1 = ref([
  { title: "John", id: 1, description: "des" },
  { title: "Joao", id: 2, description: "des" },
  { title: "Jean", id: 3, description: "des" },
  { title: "Gerard", id: 4, description: "des" },
]);

const list2 = ref([
  { title: "Juan", id: 5, description: "des" },
  { title: "Edgard", id: 6, description: "des" },
  { title: "Johnson", id: 7, description: "des" },
]);

const dragOptions = computed(() => {
  return {
    animation: 200,
    group: "task",
    disabled: false,
    ghostClass: "ghost",
  };
});

// board states
const states = ref(["TODO", "INPROGRESS", "TESTING", "DONE"]);
const cards = ref([
  {
    id: 1,
    title: "fix: 💭 channel label on chat app",
    description: "we need it bolder",
    order: 1,
    state: "TODO",
  },
  {
    id: 2,
    title: "feature: new emojis on board 🤘",
    description: "we need it for reasons 🤤",
    order: 0,
    state: "TODO",
  },
  {
    id: 3,
    title: "feature: add stripe account on signup",
    description: "",
    order: 1,
    state: "TESTING",
  },
  {
    id: 4,
    title: "refactor: scroll 📜 directive on big pages",
    description: "remember to check the scroll",
    order: 0,
    state: "INPROGRESS",
  },
  {
    id: 5,
    title: "feature: add big cards on dashboard",
    description: "everyone loves cards",
    order: 3,
    state: "TODO",
  },
  {
    id: 6,
    title: "feature: add big cards on dashboard",
    description: "everyone loves cards",
    order: 3,
    state: "TESTING",
  },
  {
    id: 7,
    title: "feature: add big cards on dashboard",
    description: "everyone loves cards",
    order: 3,
    state: "DONE",
  },
]);

const columns = ref([]);

onMounted(() => {
  initColumns();
  parseCards(cards.value);
});

// Init
const initColumns = () => {
  states.value.forEach((state, index) => {
    columns.value.push({
      key: state,
      cards: [],
      isAddVisible: false,
      callback: (e) => changeState(e, index),
    });
  });
};

const parseCards = (cards) => {
  if (!cards) return columns.value.map((column) => (column.cards = []));

  columns.value.forEach((column) => {
    column.cards = cards
      .filter((card) => card.state === column.key)
      .sort((a, b) => (a.order < b.order ? -1 : 0));
  });
};

// Add
const addCard = (column) => {
  const { addTitle, key } = column;
  if (!addTitle) return;
  let newCard = {
    id: "_" + Math.random().toString(36).substring(2, 11),
    state: key,
    title: addTitle,
    description: "",
    order: -1,
  };
  column.cards.unshift(newCard);
  column.addTitle = "";
  column.isAddVisible = false;
};

const changeState = (e, colIndex) => {
  if (e.added || e.moved) {
    const column = columns.value[colIndex];
    const state = column.key;
    for (let i = 0; i < column.cards.length; i++) {
      column.cards[i].order = i;
      column.cards[i].state = state;
    }
  }
};

// Edit
const cardToEdit = ref(null);
const title = ref("");
const description = ref("");
const editDialog = ref(false);
const showEdit = (card) => {
  cardToEdit.value = card;
  title.value = card.title;
  description.value = card.description;
  editDialog.value = true;
};

const saveCard = () => {
  let targetCard = cards.value.find((card) => card.id === cardToEdit.value.id);
  console.log(targetCard);
  if (targetCard) {
    targetCard.title = title.value;
    targetCard.description = description.value;
    editDialog.value = false;
  }
};

// Delete
const deleteDialog = ref(false);
const cardToDelete = ref(null);
const showDelete = (card) => {
  cardToDelete.value = card;
  deleteDialog.value = true;
};

const deleteCard = () => {
  deleteDialog.value = false;
  const cardIndex = cards.value.findIndex(
    (card) => card.id === cardToDelete.value.id
  );

  if (cardIndex !== -1) {
    cards.value.splice(cardIndex, 1);
  }
};

watch(cards.value, (newCards) => {
  parseCards(newCards);
});
</script>

<style lang="scss" scoped>
.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}

.board-item {
  transition: transform 0.2s;
  &:hover {
    transition: transform 0.2s;
    transform: translateY(-3px);
  }
}
.list-group {
  min-height: 100%;
}
</style>
