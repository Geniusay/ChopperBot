<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
const pricing_plans = [
  {
    plan_name: "基本版",
    price: "免费",
    storage: "15GB",
    features: ["免费15GB云存储", "文档、表格和幻灯片编辑工具", "智能应用推荐"],
  },
  // {
  //   plan_name: "高级版",
  //   price: "每月29元",
  //   storage: "100GB",
  //   features: ["100GB云存储", "专属客户支持", "高级安全功能", "高速文件传输"],
  // },
  {
    plan_name: "专业版",
    price: "每月59元",
    storage: "200GB",
    features: [
      "200GB云存储",
      "实时客户支持",
      "企业级安全功能",
      "无限制文件传输速度",
      "智能文件组织和搜索",
    ],
  },
  {
    plan_name: "企业版",
    price: "每月99元",
    storage: "2TB",
    features: [
      "2TB云存储",
      "专属客户经理",
      "企业级安全和合规性",
      "高速文件传输和无限制速度",
      "定制解决方案和集成",
    ],
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Pricing1</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    color="#F2F5F8"
    elevation="0"
    class="mx-auto landing-warpper text-left"
    rounded
  >
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10 pa-10"
    >
      <v-item-group mandatory selected-class="active-card">
        <v-row align="stretch">
          <v-col cols="12" md="4" v-for="plan in pricing_plans">
            <v-item v-slot="{ isSelected, selectedClass, toggle }">
              <v-card
                :theme="isSelected ? 'dark' : 'light'"
                elevation="0"
                height="100%"
                class="base-card text-center mx-auto pa-10 pa-md-15 d-flex flex-column justify-center"
                :class="selectedClass"
                @click="toggle"
              >
                <div>
                  <h3 class="font-weight-bold text-h5 mt-5 mb-10">
                    {{ plan.plan_name }}
                  </h3>
                  <h1 class="font-weight-black text-h3 mt-5 mb-10">
                    <span class="text-primary">{{ plan.price }}</span>
                  </h1>

                  <div class="text-h6 font-weight-bold mb-10">
                    {{ plan.storage }}
                  </div>
                  <div>
                    <div v-for="feature in plan.features">{{ feature }}</div>
                  </div>

                  <v-btn color="primary" size="large" class="mt-10">订阅</v-btn>
                </div>
              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-item-group>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.base-card {
  transition: 0.3s ease-out;
}

.active-card {
  border: 1px solid #e5e5e5;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.05);
  transform: scale(1.05);
  transition: 0.3s ease-out;
}
</style>
