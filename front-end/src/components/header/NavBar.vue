<script>
import LogoContainer from "../LogoContainer.vue";

export default {
  name: "HeaderNavBar",

  components: {
    LogoContainer,
  },

  data() {
    return {
      navLinks: [
        {
          name: "pizzas",
          text: "Pizze",
        },
      ],
    };
  },
};
</script>

<template>
  <nav>
    <LogoContainer />

    <ul>
      <li v-for="navLink in navLinks">
        <router-link
          :to="{ name: navLink.name }"
          :title="'Sezione: ' + navLink.text"
          >{{ navLink.text }}</router-link
        >
      </li>
    </ul>
  </nav>
</template>

<style scoped></style>
