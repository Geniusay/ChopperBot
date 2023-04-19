<template>
  <v-card theme="dark">
    <v-container style="max-width: 1400px" grid-list-xs>
      <v-row>
        <v-col cols="12" md="4">
          <h1 class="text-h5 my-5 d-flex align-center text-title">
            <Icon icon="logos:webix-icon" class="mr-3 my-2" />News
          </h1>
          <v-card
            elevation="0"
            class="my-5 pa-3"
            v-for="card in newsCards"
            :key="card.id"
            color="secondary"
          >
            <v-card-text class="text-content">
              <v-row align="center" no-gutters>
                <v-col cols="2" class="text-left">
                  <Icon :icon="card.icon" class="text-h4" />
                </v-col>
                <v-col class="text-left" cols="10">
                  {{ card.content }}
                </v-col>
              </v-row>
              <v-row align="center" no-gutters>
                <v-col cols="3" class="text-left">
                  <span>12:24</span>
                </v-col>
                <v-col class="text-h5 text-right" cols="9">
                  <v-btn variant="text" color="accent">Read➧</v-btn>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" md="8" class="text-left">
          <h1 class="text-h5 my-5 d-flex align-center text-title">
            <Icon icon="logos:archlinux" class="mr-3 my-2" />TimeLine
          </h1>
          <!-- Info Card1 -->
          <v-card elevation="0" color="secondary" class="info-card gradient">
            <v-card-text class="d-flex align-center text-content">
              <Icon class="mr-5 text-h4" icon="logos:browserslist" />
              <span>Write something...</span>
              <v-spacer></v-spacer>
              <v-btn
                variant="text"
                icon="mdi-account-search"
                @click="toggleTheme"
              ></v-btn>
              <v-btn variant="text" icon="mdi-pin"></v-btn>
            </v-card-text>
            <v-card-actions class="r py-5 text-title">
              <div></div>
              <v-select
                variant="outlined"
                placeholder="who can see"
                density="compact"
                hide-details
                class="mr-2"
                :items="[
                  'California',
                  'Colorado',
                  'Florida',
                  'Georgia',
                  'Texas',
                  'Wyoming',
                ]"
              ></v-select>
              <v-select
                variant="outlined"
                placeholder="channel"
                density="compact"
                hide-details
                :items="[
                  'California',
                  'Colorado',
                  'Florida',
                  'Georgia',
                  'Texas',
                  'Wyoming',
                ]"
              ></v-select>

              <v-spacer></v-spacer>
              <v-btn variant="tonal" icon="mdi-heart" color="accent"></v-btn>
            </v-card-actions>
          </v-card>

          <!-- Info Card 2 -->
          <v-card color="secondary" class="info-card">
            <div class="card-top r text-content">
              <Icon class="mr-5 text-h4" icon="logos:capacitorjs-icon" />
              <div class="flex-1">
                Who has the advantage now? Who do you think will win the
                election?
              </div>
              <span>9:31 am</span>
            </div>
            <v-card-text class="text-content">
              <div>
                <v-radio-group color="accent" v-model="favoriteRadios">
                  <template v-slot:label>
                    <div>Your favorite <strong>search engine</strong></div>
                  </template>
                  <v-radio value="Google">
                    <template v-slot:label>
                      <v-row align="center">
                        <v-col cols="6" sm="5" md="3">
                          <span> Of course it's</span>
                          <strong class="ml-1 text-info">Google</strong></v-col
                        >
                        <v-col cols="4" sm="5" md="7">
                          <v-progress-linear
                            model-value="79"
                            color="#E89888"
                            rounded
                          ></v-progress-linear
                        ></v-col>
                        <v-col cols="2">79%</v-col>
                      </v-row>
                    </template>
                  </v-radio>
                  <v-radio value="Duckduckgo">
                    <template v-slot:label>
                      <v-row align="center">
                        <v-col cols="6" sm="5" md="3">
                          <span> Of course it's</span>
                          <strong class="ml-1 text-info">Meta</strong></v-col
                        >
                        <v-col cols="4" sm="5" md="7">
                          <v-progress-linear
                            model-value="21"
                            color="#B931F0"
                            rounded
                          ></v-progress-linear
                        ></v-col>
                        <v-col cols="2">70%</v-col>
                      </v-row>
                      <div></div>
                    </template>
                  </v-radio>
                </v-radio-group>
                <div class="d-flex justify-space-between align-center">
                  <span>9850 votes.Final result</span>
                  <v-btn variant="outlined" color="accent">Vote</v-btn>
                </div>
              </div>
            </v-card-text>
            <v-card-actions class="r text-content">
              <v-btn prepend-icon="mdi-heart"> Like(2.7k)</v-btn>
              <v-btn prepend-icon="mdi-comment">CommentLike(35)</v-btn>
              <v-btn prepend-icon="mdi-share">ShareLike(99)</v-btn>
            </v-card-actions>
          </v-card>
          <!-- Info Card3 -->
          <v-card color="secondary" class="info-card">
            <div class="card-top r text-content">
              <Icon class="text-h4" icon="logos:engine-yard-icon" />
              <div class="flex-1 mx-4">
                <p>
                  <span class="text-accent">Elon Mask</span> shared a
                  <span class="text-accent">link</span>
                </p>
                <p>
                  Who has the advantage now? Who do you think will win the
                  election?
                </p>
              </div>

              <span>9:31 am</span>
            </div>
            <v-card-text class="">
              <v-card>
                <v-img
                  class="align-end text-white"
                  height="200"
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  cover
                >
                  <v-card-title>Top 10 Australian beaches</v-card-title>
                </v-img>
                <v-card-subtitle class="pt-4"> Number 10 </v-card-subtitle>

                <v-card-text>
                  <div>Whitehaven Beach</div>

                  <div>Whitsunday Island, Whitsunday Islands</div>
                </v-card-text>
              </v-card>

              <v-card class="d-flex mt-5">
                <v-img
                  max-width="200"
                  aspect-ratio="1"
                  cover
                  src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                >
                </v-img>

                <div class="pa-2 flex-1">
                  <v-card-subtitle class="pt-4"> Number 10 </v-card-subtitle>
                  <v-card-text>
                    <div>Whitehaven BeachAA</div>
                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>
                  <v-card-actions>
                    <span>Nov.1.2016</span>
                    <v-spacer></v-spacer>
                    <span class="text-accent">BBC.COM</span>
                  </v-card-actions>
                </div>
              </v-card>
            </v-card-text>
            <v-card-actions class="r text-content">
              <v-btn color="#D84B82" prepend-icon="mdi-heart">
                Like(2.7k)</v-btn
              >
              <v-btn prepend-icon="mdi-comment">CommentLike(35)</v-btn>
              <v-btn prepend-icon="mdi-share">ShareLike(99)</v-btn>
            </v-card-actions>
          </v-card>
          <!-- Info Card4 -->
          <v-card
            color="secondary"
            class="info-card d-flex justify-space-between align-center pa-5 text-content"
          >
            <v-avatar size="50" color="red">
              <v-img
                src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"
              ></v-img>
            </v-avatar>
            <span class="text-h4 mx-5">67903</span>
            <div class="flex-1 text-content">
              people viewed your profile in the past 90 days
            </div>
            <v-img
              class="d-none d-md-block"
              max-width="50"
              src="https://cdn.vuetifyjs.com/images/cards/foster.jpg"
            ></v-img>
          </v-card>
          <!-- Card5 -->
          <v-card
            class="gradient success info-card d-flex justify-space-between align-center pa-5 text-content"
            color=""
          >
            <v-table>
              <thead>
                <tr>
                  <th class="text-left">Name</th>
                  <th class="text-left">Calories</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in desserts" :key="item.name">
                  <td>{{ item.name }}</td>
                  <td>{{ item.calories }}</td>
                </tr>
              </tbody>
            </v-table>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script setup lang="ts">
import { Icon } from "@iconify/vue";
import { useTheme } from "vuetify";

const theme = useTheme();

const toggleTheme = () => {
  theme.global.name.value = theme.global.current.value.dark ? "light" : "dark";
};
const newsCards = [
  {
    id: 1,
    title: "Alfresco",
    icon: "logos:alfresco",
    content: "Secure Content Management Solutions for a Distributed Workforce",
  },
  {
    id: 2,
    title: "Zulip",
    icon: "logos:zulip-icon",
    content: "Chat for distributed teams.",
  },
  {
    id: 3,
    title: "Authy",
    icon: "logos:authy",
    content: "Enable 2FA for your favorite sites",
  },
  {
    id: 4,
    title: "Appveyor",
    icon: "logos:appveyor",
    content: "Build, test, deploy your apps faster, on any platform.",
  },
  {
    id: 5,
    title: "Ant-design",
    icon: "logos:ant-design",
    content:
      "Help designers/developers building beautiful products more flexible and working with happiness",
  },
];

const favoriteRadios = ref("Duckduckgo");

const desserts = [
  {
    name: "Frozen Yogurt",
    calories: 159,
  },
  {
    name: "Ice cream sandwich",
    calories: 237,
  },
  {
    name: "Eclair",
    calories: 262,
  },
  {
    name: "Cupcake",
    calories: 305,
  },
  {
    name: "Gingerbread",
    calories: 356,
  },
  {
    name: "Jelly bean",
    calories: 375,
  },
  {
    name: "Lollipop",
    calories: 392,
  },
  {
    name: "Honeycomb",
    calories: 408,
  },
  {
    name: "Donut",
    calories: 452,
  },
  {
    name: "KitKat",
    calories: 518,
  },
];
</script>

<style lang="scss" scoped>
.bg {
  text-align: left;
  height: 100vh;
  // 更新
  // background-image: linear-gradient(135deg, #1c1e30, #2f3050);
}

.info-card {
  margin-bottom: 2rem;

  .v-card-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2rem;
    font-size: 1rem;
  }

  .card-top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2rem;
    font-size: 1rem;
  }

  .v-card-text {
    padding: 2rem;
  }

  .v-card-actions {
    display: flex;
    align-items: center;
    padding: 1.5rem 1rem;
    font-size: 1rem;
  }
}
</style>
