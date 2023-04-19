<template>
  <div>
    <v-card theme="dark" class="search-card">
      <v-text-field
        label="search"
        :loading="loading"
        append-inner-icon="mdi-magnify"
        @click:append-inner="search"
        v-model="searchKey"
      ></v-text-field>
    </v-card>

    <v-row>
      <v-col cols="12" md="3" xl="2">
        <div class="text-subtitle-1 my-5 ml-2">Category</div>
        <div v-for="faq in computedFaqs" :key="faq.id">
          <v-btn variant="text" color="primary" @click="goTo(faq.id)">{{
            faq.title
          }}</v-btn>
        </div>
      </v-col>
      <v-col cols="12" md="9" xl="10">
        <div v-for="faq in computedFaqs" :id="faq.id" :key="faq.id">
          <div id="#general" class="text-subtitle-2 my-5 ml-2">
            {{ faq.title }}
          </div>
          <v-expansion-panels>
            <v-expansion-panel
              v-for="item in faq.items"
              :key="item.id"
              :title="item.title"
              :text="item.content"
            ></v-expansion-panel>
          </v-expansion-panels>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
const router = useRouter();
const searchKey = ref("");
const loaded = ref(false);
const loading = ref(false);
const faqs = ref([
  {
    id: "general",
    title: "General Resources",
    items: [
      {
        title:
          "Can I use a purchased item in a freelance project or contract work for a client?",
        content:
          "Yes. However, if the client intends to charge End Users in any way from the End Product you create, you will need to purchase an Extended License. If you create the End Product for a client, your rights to purchased Items are transferred from you to your client.",
      },
      {
        title: "What is an End Product?",
        content:
          "An End Product is work that is designed or developed for a single, paid client. This website can not be resold as a product to multiple users. For more information on selling products to multiple users.",
      },
      {
        title: "What are the End Product requirements?",
        content:
          "An End Product must be a unique implementation of the Item, often requiring limited copy and content changes. For example, if you purchase a resume template, you may use the Item for yourself or a client after having input personal information (you may not resell it as stock).",
      },
      {
        title:
          "What is Personal Use, Commerical Work, Contracted Work, Client Work, etc.?",
        content:
          "If the created site can not charge users in any way, it is considered for Personal Use and a Regular License can be used. For End Products that can charge users, such as a Software as a Service application, or an e-commerce site, you should use an Extended License. For any End Products that will be sold in its entirety, such as creating software that is distributed digitally, use an Unlimited License.",
      },
      {
        title: "What is Personal Use?",
        content:
          "A Personal Use License can only be used for 1 End Product that does not charge users in any way.",
      },
      {
        title: "What is Commerical Use?",
        content:
          "A Commercial Use License can only be used for 1 End Product that charges or will charge users.",
      },
      {
        title: "What is Unlimited Use?",
        content:
          "An Extended Use License can be used for any number of Personal and Commercial projects.",
      },
    ],
  },
  {
    id: "licenses",
    title: "Licenses",
    items: [
      {
        title: "Personal Use",
        content:
          "Digital products purchased under the Standard License may be used one time in an End Product for Personal Use (an End Product that does not charge End Users).",
      },
      {
        title: "Commercial Use",
        content:
          "Digital products purchased under the Extended License may be used an unlimited amount of times for Personal Use, and one time to create a single End Product that does charge End Users.",
      },
      {
        title: "Unlimited Use",
        content:
          "An Unlimited License can be used an unlimited amount of times for Personal Use, Commercial Use, and Commercial End Products.",
      },
    ],
  },
]);

const computedFaqs = computed(() => {
  let filteredFaqs = [];
  faqs.value.forEach((faq) => {
    let filteredItem = faq.items.filter((item) =>
      item.title?.toLowerCase().includes(searchKey.value)
    );
    if (filteredItem) {
      filteredFaqs.push({
        id: faq.id,
        title: faq.id,
        items: filteredItem,
      });
    }
  });
  return filteredFaqs;
});

const search = () => {
  loading.value = true;
  setTimeout(() => {
    loading.value = false;
    loaded.value = true;
  }, 2000);
};

const goTo = (id) => {
  document.getElementById(id).scrollIntoView({ behavior: "smooth" });
};
</script>

<style scoped>
.search-card {
  background-image: linear-gradient(135deg, #141e30, #243b55);
  padding: 2rem;
}
</style>
