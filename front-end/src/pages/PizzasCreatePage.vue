<script>
import { store } from "../store";
import axios from "axios";

export default {
  name: "PizzasCreatePage",

  data() {
    return {
      store,
      pizza: {
        name: "",
        description: "",
        imgUrl: "",
        price: "",
      },
      apiUrlSpecificSection: "/pizzas/store",
    };
  },

  methods: {
    storePizzaInDb() {
      axios
        .post(this.store.API_URL + this.apiUrlSpecificSection + this.pizza, {
          params: {},
        })
        .then((response) => {
          console.log(response.data);
          this.$router.push("/");
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<template>
  <form @submit.prevent="storePizzaInDb">
    <div class="my_form-el">
      <input
        type="text"
        name="name"
        v-model="pizza.name"
        placeholder="Inserisci nome"
      />
    </div>
    <div class="my_form-el">
      <textarea
        name="description"
        id=""
        cols="30"
        rows="10"
        placeholder="Inserisci descrizione"
        v-model="pizza.description"
        >{{ pizza.description }}</textarea
      >
    </div>
    <div class="my_form-el">
      <input
        type="text"
        name="imgUrl"
        v-model="pizza.imgUrl"
        placeholder="Inserisci l'url dell'immagine"
      />
    </div>
    <div class="my_form-el">
      <input
        type="number"
        name="price"
        v-model="pizza.price"
        placeholder="Inserisci il prezzo"
        step="0.01"
      />
    </div>

    <button type="submit">Crea Pizza</button>
  </form>
</template>

<style scoped></style>
