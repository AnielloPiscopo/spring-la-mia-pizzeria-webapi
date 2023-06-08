import { createRouter, createWebHistory } from "vue-router";

import HomePage from "./pages/HomePage.vue";
import PizzasListPage from "./pages/PizzasListPage.vue";
import PizzasCreatePage from "./pages/PizzasCreatePage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomePage,
    },

    {
      path: "/pizze",
      name: "pizzas",
      component: PizzasListPage,
    },

    {
      path: "/pizze/aggiungi",
      name: "pizzaCreate",
      component: PizzasCreatePage,
    },
  ],
});

export { router };
