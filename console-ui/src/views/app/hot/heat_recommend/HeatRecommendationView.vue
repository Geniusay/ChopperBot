<template>
  <!-- board column -->
  <v-row style="min-width: 1000px">
    <v-col
      cols="4"
      v-for="column in columns"
      :key="column.platform"
      class="pa-3 flex-1"
    >
      <div class="d-flex align-center">
        <h5 class="font-weight-bold">{{ column.platform }}</h5>
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
          v-model="column.platform"
          label="平台"
          variant="underlined"
          hideDetails
          disabled
          placeholder="Input title for this card"
          autofocus
        ></v-text-field>
        <v-text-field
          color="primary"
          v-model="column.addCard.moduleName"
          label="类型"
          variant="underlined"
          hideDetails
          placeholder="输入你要推荐的直播类型（例如：英雄联盟，以平台类型为基准）"
          autofocus
        ></v-text-field>
        <v-text-field
          color="primary"
          v-model="column.addCard.top"
          label="Top"
          variant="underlined"
          hideDetails
          placeholder="输入该推荐该类型的前几个直播"
          autofocus
        ></v-text-field>
        <v-text-field
          color="primary"
          v-model="column.addCard.banLiver"
          label="Ban"
          variant="underlined"
          hideDetails
          placeholder="输入需要ban掉的主播,支持正则表达式"
          autofocus
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
          <follow-dog-card
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
        <span class="flex-1">Edit FollowDog 🐶</span>
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
          v-model="cardToEdit.platform"
          label="平台"
          variant="plain"
          hideDetails
          placeholder="输入平台"
          autofocus
          disabled
        ></v-text-field>
        <v-divider></v-divider>
        <v-text-field
          class="py-2 px-1"
          color="primary"
          v-model="cardToEdit.moduleName"
          label="类型"
          variant="plain"
          hideDetails
          placeholder="输入你要推荐的直播类型（例如：英雄联盟，以平台类型为基准）"
          autofocus
        ></v-text-field>
        <v-divider></v-divider>
        <v-text-field
          class="py-2 px-1"
          color="primary"
          v-model="cardToEdit.top"
          label="Top"
          variant="plain"
          hideDetails
          placeholder="输入该推荐该类型的前几个直播"
          autofocus
        ></v-text-field>
        <v-divider></v-divider>
        <v-text-field
          class="py-2 px-1"
          color="primary"
          v-model="cardToEdit.banLiver"
          label="Ban"
          variant="plain"
          hideDetails
          placeholder="输入需要ban掉的主播,支持正则表达式"
          autofocus
        ></v-text-field>
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
  <v-dialog v-model="deleteDialog" max-width="600">
    <v-card>
      <v-card-title class="text-headline">Delete</v-card-title>
      <v-card-text>确定要删除{{cardToDelete.dogId}}吗?</v-card-text>
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
import FollowDogCard from "@/views/app/hot/heat_recommend/compoments/FollowDogCard.vue";
import {getAllConfigModule,getAllConfigFiles} from "@/api/FileController";
import {addFollowDog, deleteFollowDog, getFollowDogs, updateFollowDog} from "@/api/hot/heatRecommendApi";
import {useHotRecommendStore} from "@/views/app/hot/heat_recommend/heatRecommendationStore";
import {FollowDog} from "@/views/app/hot/heat_recommend/FollowDogTypes";
import {useSnackbarStore} from "@/stores/snackbarStore";
const hotRecommendStore = useHotRecommendStore()
const snackbarStore = useSnackbarStore();
const dragOptions = computed(() => {
  return {
    animation: 200,
    group: "loadTask",
    disabled: false,
    ghostClass: "ghost",
  };
});

const platforms = hotRecommendStore.platforms

const default_card = ref<FollowDog>({
  id:0,
  dogId:'',
  platform: '',
  moduleName: 'all',
  top: 5,
  banLiver:'',
})

type Column = {
  platform: string,
  cards: FollowDog[],
  isAddVisible: false,
  callback: Function,
  addCard: FollowDog
}

const columns = ref<Column[]>([]);
const cards = ref<FollowDog[]>([]);
const deleteDialog = ref(false);
const cardToDelete = ref<FollowDog>();
const cardToEdit = ref<FollowDog>();
const title = ref("");
const description = ref("");
const editDialog = ref(false);

onMounted(async () => {
  cards.value = await initFollowDog();
  initColumns();
  parseCards(hotRecommendStore.followDogs);
});
// Init 模块类型
const initFollowDog = async()=>{
  return await getFollowDogs().then(res=>{
     hotRecommendStore.followDogs = res.data['list'];
     return res.data['list'];
  })
}
// Init 配置文件


const initColumns = () => {
  platforms.forEach((state, index) => {
    columns.value.push({
      platform: state,
      cards: [],
      isAddVisible: false,
      callback: (e) => changeState(e, index),
      addCard: Object.assign({},default_card.value),
    } );
  });
};

const parseCards = (cards) => {
  if (!cards) return columns.value.map((column) => (column.cards = []));
  columns.value.forEach((column) => {
    column.cards = cards
      .filter((card) => card.platform === column.platform)
      .sort((a, b) => (a.order < b.order ? -1 : 0));
  });
};

// Add
const addCard = async (column) => {
  if (!column.addCard) return;
  column.addCard.platform = column.platform
  await addFollowDog(column.addCard).then(res=>{
    if(res.code==='200'){
      column.cards.push(res.data["add"])
      hotRecommendStore.addFollowDog(res.data["add"])
      snackbarStore.showSuccessMessage("添加成功")
    }else{
      snackbarStore.showErrorMessage("添加失败")
    }
  })
  column.addCard = Object.assign({},default_card.value)
  column.isAddVisible = false
};

const changeState = async (e, colIndex) => {
  if (e.added || e.moved) {
    const column = columns.value[colIndex];
    const state = column.platform;
    for (let i = 0; i < column.cards.length; i++) {
      column.cards[i].order = i;
      if(column.cards[i].platform!=state){
        column.cards[i].platform = state;
        await updateFollowDog(column.cards[i]).then(res=>{
          if(res?.data['success']){
            hotRecommendStore.updateFollowDog(column.cards[i])
            snackbarStore.showSuccessMessage("更新成功")
          }else{
            snackbarStore.showErrorMessage("更新失败")
          }
        })
      }

    }
  }
};

// Edit

const showEdit = (card) => {
  cardToEdit.value = Object.assign({},card);
  editDialog.value = true;
};

const saveCard = async () => {
  if(cardToEdit.value!=null){
    await updateFollowDog(cardToEdit.value).then(res=>{
      if(res?.data['success']){
        const column = columns.value.find(column=>column.platform===cardToEdit.value.platform)
        if(column!=null){
          const old = column.cards.find(dog=>dog.dogId===cardToEdit.value.dogId)
          column.cards.splice(column.cards.indexOf(old),1,cardToEdit.value)
        }
        hotRecommendStore.updateFollowDog(cardToEdit.value)
        snackbarStore.showSuccessMessage("更新成功")
      }else{
        snackbarStore.showErrorMessage("更新失败")
      }
    })
  }
  editDialog.value = false;
};

// Delete

const showDelete = (card) => {
  cardToDelete.value = card;
  deleteDialog.value = true;
};

const deleteCard = async () => {
  deleteDialog.value = false;
  await deleteFollowDog(cardToDelete.value.dogId,cardToDelete.value.platform).then(res=>{
    if(res?.data['success']){
      hotRecommendStore.deleteFollowDog(cardToDelete.value)
      columns.value.forEach((column) => {
        if(column.platform===cardToDelete.value.platform){
          column.cards = column.cards
            .filter((card) => cardToDelete.value.dogId !== card.dogId)
        }
      });
      snackbarStore.showSuccessMessage("删除成功")
    }else{
      snackbarStore.showErrorMessage("删除失败")
    }
  })

};

watch(hotRecommendStore.followDogs, (newCards) => {
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
