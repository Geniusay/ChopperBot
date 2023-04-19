<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts"></script>

<template>
  <span class="loader"></span>
</template>

<style scoped lang="scss">
.loader {
  width: 200px;
  height: 140px;
  background: #979794;
  box-sizing: border-box;
  position: relative;
  border-radius: 8px;
  perspective: 1000px;
}

.loader:before {
  content: "";
  position: absolute;
  left: 10px;
  right: 10px;
  top: 10px;
  bottom: 10px;
  border-radius: 8px;
  background: #f5f5f5 no-repeat;
  background-size: 60px 10px;
  background-image: linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0);

  background-position: 15px 30px, 15px 60px, 15px 90px, 105px 30px, 105px 60px,
    105px 90px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
}
.loader:after {
  content: "";
  position: absolute;
  width: calc(50% - 10px);
  right: 10px;
  top: 10px;
  bottom: 10px;
  border-radius: 8px;
  background: #fff no-repeat;
  background-size: 60px 10px;
  background-image: linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0),
    linear-gradient(#ddd 100px, transparent 0);
  background-position: 50% 30px, 50% 60px, 50% 90px;
  transform: rotateY(0deg);
  transform-origin: left center;
  animation: paging 1s linear infinite;
}

@keyframes paging {
  to {
    transform: rotateY(-180deg);
  }
}
</style>
