<script>
import { store } from "../store";
import axios from "axios";

export default {
  name: "PizzasListPage",

  data() {
    return {
      store,
      deleted: false,
      pizzasList: [],
      apiUrlSpecificSection: "/pizzas",
    };
  },

  methods: {
    getPizzasInfo() {
      axios
        .get(this.store.API_URL + this.apiUrlSpecificSection, {
          params: {},
        })
        .then((response) => {
          console.log(response.data);
          this.pizzasList = response.data;
        })
        .catch((err) => console.log(err));
    },
    deletePizza(id) {
      axios
        .delete(this.store.API_URL + this.apiUrlSpecificSection + id)
        .then((response) => (this.deleted = true))
        .catch((error) => console.log(error));
    },
  },

  created() {
    this.getPizzasInfo();
  },
};
</script>

<template>
  <div>
    <router-link :to="{ name: 'pizzaCreate' }">Aggiungi pizza</router-link>
  </div>
  <div>
    <ul>
      <li v-for="pizza in pizzasList">
        <div>
          <div>{{ pizza.name }}</div>
          <div>{{ pizza.description }}</div>
          <div>{{ pizza.imgUrl }}</div>
          <div>{{ pizza.price }} &euro;</div>
          <div>{{ pizza.discountedPrice }} &euro;</div>
        </div>
        <div>
          <span @click="deletePizza()">Elimina</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<style scoped></style>
