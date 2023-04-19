<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { RouterLink } from "vue-router";

const navs = [
  {
    title: "サービス",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "配送",
        to: "/",
      },
      {
        title: "配送状況の確認",
        to: "/",
      },
      {
        title: "お支払い方法",
        to: "/",
      },
      {
        title: "プランニング",
        to: "/",
      },
      {
        title: "アプリ",
        to: "/",
      },
      {
        title: "ギフトリスト",
        to: "/",
      },
      {
        title: "店舗検索",
        to: "/",
      },

      {
        title: "全て見る",
        to: "/",
      },
    ],
  },
  {
    title: "サポート",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "よくある質問",
        to: "/",
      },
      {
        title: "お問い合わせ",
        to: "/",
      },
      {
        title: "返品",
        to: "/",
      },
      {
        title: "品質保障",
        to: "/",
      },
      {
        title: "ご意見.ご要望",
        to: "/",
      },
    ],
  },
  {
    title: "イクラについて",
    icon: "mdi-home",
    to: "/",
    subNavs: [
      {
        title: "イクラの理念",
        to: "/",
      },
      {
        title: "サステナビリティ",
        to: "/",
      },
      {
        title: "採用情報",
        to: "/",
      },
      {
        title: "ニュースルーム",
        to: "/",
      },
      {
        title: "Life at Home Report",
        to: "/",
      },
      {
        title: "IKEAカタログ",
        to: "/",
      },
    ],
  },
];

const socials = [
  {
    icon: "mdi-facebook",
    to: "/",
  },
  {
    icon: "mdi-twitter",
    to: "/",
  },
  {
    icon: "mdi-instagram",
    to: "/",
  },
  {
    icon: "mdi-youtube",
    to: "/",
  },
  {
    icon: "mdi-linkedin",
    to: "/",
  },
  {
    icon: "mdi-pinterest",
    to: "/",
  },

  {
    icon: "mdi-reddit",
    to: "/",
  },

  {
    icon: "mdi-whatsapp",
    to: "/",
  },

  {
    icon: "mdi-wechat",
    to: "/",
  },

  {
    icon: "mdi-rss",
    to: "/",
  },
];

const footerNavs = [
  {
    title: "プライバシーポリシー ",
    to: "/",
  },
  { title: "利用規約 ", to: "/" },
  { title: "Cookieポリシー ", to: "/" },
  { title: "Cookie設定 ", to: "/" },
  { title: "特定商取引法に基づく表記  ", to: "/" },
  { title: "古物営業法に基づく表記 ", to: "/" },
];
</script>

<template>
  <v-sheet elevation="0" class="mx-auto text-body-2" color="#F5F5F5" rounded>
    <v-sheet
      class="mx-auto text-left pa-10"
      color="transparent"
      elevation="0"
      max-width="1600"
    >
      <v-row>
        <v-col cols="12" md="4">
          <div>
            <h6 class="font-weight-bold">IKEA Familyに入会する</h6>
            <p class="my-4 text-body-2 pr-10">
              IKEA
              Familyメンバーになると、お買い得な商品や便利なサービスなど、さまざまな会員特典をご利用いただけます。入会費・年会費無料。
            </p>

            <p>
              <RouterLink to="/ " class="text-decoration-underline text-body-2"
                >詳しく見る</RouterLink
              >
            </p>
            <v-btn
              class="text-body-2 font-weight-bold my-5"
              color="black"
              rounded="pill"
              >入会する</v-btn
            >
          </div>
          <div>
            <h6 class="font-weight-bold">IKEA Business Networkに入会する</h6>
            <p class="my-4 text-body-2 pr-10">
              IKEA Business
              Networkメンバーになると、ビジネスに役立つさまざまな会員特典をご利用いただけます。イケアはビジネスの成長のサポートします。入会費・年会費無料。
            </p>

            <p>
              <RouterLink to="/ " class="text-decoration-underline text-body-2"
                >詳しく見る</RouterLink
              >
            </p>
            <v-btn
              class="text-body-2 font-weight-bold my-5"
              color="black"
              rounded="pill"
              >入会する</v-btn
            >
          </div>
        </v-col>
        <v-col cols="12" md="8">
          <v-row>
            <v-col cols="12" md="4" v-for="nav in navs">
              <p class="text-body-2 font-weight-bold mb-4">
                <b>{{ nav.title }}</b>
              </p>
              <p
                class="text-body-2 mb-4"
                v-for="subNav in nav.subNavs"
                :key="subNav.title"
              >
                <router-link :to="nav.to">
                  {{ subNav.title }}
                </router-link>
              </p>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <div class="d-flex flex-wrap algin-center justify-space-between">
        <div class="socials">
          <v-icon
            v-for="social in socials"
            :key="social.icon"
            :icon="social.icon"
            class="ma-3"
            color="black"
            size="26"
          ></v-icon>
        </div>
        <div class="international">
          <v-btn
            variant="outlined"
            color="grey-darken-2"
            rounded="pill"
            prepend-icon="mdi-earth"
            class="text-body-2 mr-5"
            >国の変更</v-btn
          >
          <v-btn
            variant="outlined"
            color="grey-darken-2"
            rounded="pill"
            append-icon="mdi-chevron-down"
            class="text-body-2"
            >日本語-Japanese</v-btn
          >
        </div>
      </div>
      <hr class="my-3" />
      <div
        class="subNavs mt-5 d-flex justify-space-between align-center flex-wrap"
      >
        <span class="my-5">© Inter IKEA Systems B.V 1999-2023 </span>
        <div class="my-5">
          <a class="mr-2" href="#" v-for="nav in footerNavs">{{ nav.title }}</a>
        </div>
      </div>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.subNavs {
  font-size: 12px;
}
</style>
