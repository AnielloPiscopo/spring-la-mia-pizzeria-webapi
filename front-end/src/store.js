import { reactive } from "vue";

export const store = reactive({
  API_URL: "http://localhost:8080/api/v1",

  upperCaseTheFirstLetter(str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  },
});
