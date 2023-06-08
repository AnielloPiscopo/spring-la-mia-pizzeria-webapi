<script>
import AppHeader from "./components/AppHeader.vue";

export default {
  components: {
    AppHeader,
  },
};
</script>

<template>
  <AppHeader />
  <main>
    <router-view></router-view>
  </main>
</template>

<style></style>
