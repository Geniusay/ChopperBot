<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import FeatureCard from "./components/FeatureCard.vue";
import FeatureLoadMore from "./components/FeatureLoadMore.vue";
import FeatureLotte from "./components/FeatureLotte.vue";
import FeatureChatBot from "./components/FeatureChatBot.vue";
import FeatureDynamicScroller from "./components/FeatureDynamicScroller.vue";
</script>

<template>
  <FeatureCard title="LoadMore">
    <FeatureLoadMore />
  </FeatureCard>
  <FeatureCard title="LotteAnimation">
    <FeatureLotte />
  </FeatureCard>
  <FeatureCard title="ChatBot">
    <FeatureChatBot />
  </FeatureCard>
  <FeatureCard title="DynamicScroller">
    <FeatureDynamicScroller />
  </FeatureCard>
</template>

<style scoped lang="scss"></style>
