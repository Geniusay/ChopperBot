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
  width: 48px;
  height: 48px;
  margin: auto;
  position: relative;
}
.loader:before {
  content: "";
  width: 48px;
  height: 5px;
  background: #000;
  opacity: 0.25;
  position: absolute;
  top: 60px;
  left: 0;
  border-radius: 50%;
  animation: shadow 0.5s linear infinite;
}
.loader:after {
  content: "";
  width: 100%;
  height: 100%;
  background: #29b6f6;
  animation: bxSpin 0.5s linear infinite;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 4px;
}
@keyframes bxSpin {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 40px;
  }
  75% {
    transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    transform: translateY(0) rotate(90deg);
  }
}

@keyframes shadow {
  0%,
  100% {
    transform: scale(1, 1);
  }
  50% {
    transform: scale(1.2, 1);
  }
}
</style>
