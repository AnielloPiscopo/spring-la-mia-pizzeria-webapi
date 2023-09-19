<div align="center">
<h1 align="center">
<img src="https://img.shields.io/badge/Spring-4FC08D.svg?style&logo=Spring&logoColor=white" width="100" />
<br>SPRING-LA-MIA-PIZZERIA-WEBAPI
</h1>
<h3>◦ Simple exercise that contains a webpage for the pizzas of a series of restaurant with front-end and back-end sections</h3>
<h3>◦ Developed during the course of Java Web Developer with Experis</h3>

<p align="center">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E.svg?style&logo=JavaScript&logoColor=black" alt="JavaScript" />
<img src="https://img.shields.io/badge/HTML5-E34F26.svg?style&logo=HTML5&logoColor=white" alt="HTML5" />
<img src="https://img.shields.io/badge/CSS3-E34F26.svg?style&logo=CSS3&logoColor=white" alt="CSS3" />
<img src="https://img.shields.io/badge/Vite-646CFF.svg?style&logo=Vite&logoColor=white" alt="Vite" />
<img src="https://img.shields.io/badge/Axios-5A29E4.svg?style&logo=Axios&logoColor=white" alt="Axios" />

<img src="https://img.shields.io/badge/Vue.js-4FC08D.svg?style&logo=vuedotjs&logoColor=white" alt="Vue.js" />
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style&logo=openjdk&logoColor=white" alt="java" />
<img src="https://img.shields.io/badge/Spring-4FC08D.svg?style&logo=Spring&logoColor=white" alt="Spring" />
<img src="https://img.shields.io/badge/JSON-000000.svg?style&logo=JSON&logoColor=white" alt="JSON" />
<img src="https://img.shields.io/badge/Markdown-000000.svg?style&logo=Markdown&logoColor=white" alt="Markdown" />
</p>
<img src="https://img.shields.io/github/languages/top/AnielloPiscopo/spring-la-mia-pizzeria-webapi?style&color=5D6D7E" alt="GitHub top language" />
<img src="https://img.shields.io/github/languages/code-size/AnielloPiscopo/spring-la-mia-pizzeria-webapi?style&color=5D6D7E" alt="GitHub code size in bytes" />
<img src="https://img.shields.io/github/commit-activity/m/AnielloPiscopo/spring-la-mia-pizzeria-webapi?style&color=5D6D7E" alt="GitHub commit activity" />
<img src="https://img.shields.io/github/license/AnielloPiscopo/spring-la-mia-pizzeria-webapi?style&color=5D6D7E" alt="GitHub license" />
</div>

---

## 📒 Table of Contents
- [📒 Table of Contents](#-table-of-contents)
- [🧩 Modules](#modules)
- [🚀 Getting Started](#-getting-started)

---

## 🧩 Modules

<details closed><summary>Root</summary>

| File                                                                                                                                                                                                 | Summary                   |
| ---                                                                                                                                                                                                  | ---                       |
| [index.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\index.html)                                                                                         | This is the main HTML entry point for your web application. It's where you define the structure of your HTML document, include CSS and JavaScript files, and specify the root element where the Vue.js application will be mounted. |
| [vite.config.js](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\vite.config.js)                                                                                 | This is a configuration file used with Vite, which is a build tool and development server designed for modern web development. |
| [App.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\App.vue)                                                                                           | This is the base of the work and it serves as the root component of the Vue.js application and contains the overall layout, navigation, and the top-level structure of your app. |
| [main.js](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\main.js)                                                                                           | This file is the entry point of the application. It's where you create and configure the Vue instance, set up routing (if used), and specify which component to render in the root DOM element. |
| [router.js](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\router.js)                                                                                       | This file contains the routes for the pages and sections of the front-end. |
| [store.js](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\store.js)                                                                                         | This file contains the global and general variables and functions of the work. |
| [AppHeader.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\components\AppHeader.vue)                                                                    | This is the component that represents the header tag of the webpage. |
| [LogoContainer.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\components\LogoContainer.vue)                                                            | This is the component for the logo |
| [NavBar.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\components\header\NavBar.vue)                                                                   | This is the component for the navbar. |
| [HomePage.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\pages\HomePage.vue)                                                                           | This is the component for the HomePage of the front-end. |
| [PizzasCreatePage.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\pages\PizzasCreatePage.vue)                                                           | This is the page in the front-end dedicated to the creation of a pizza element. |
| [PizzasEditPage.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\pages\PizzasEditPage.vue)                                                               | This is the page in the front-end dedicated to the updated of a pizza element. |
| [PizzasListPage.vue](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/front-end\src\pages\PizzasListPage.vue)                                                               | This is the page in the front-end dedicated to the view of the pizza elements. |
| [SpringLaMiaPizzeriaCrudApplication.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\SpringLaMiaPizzeriaCrudApplication.java)           | This is the entry point of the application, containing the main method. This method starts the application, automatically configuring the Spring environment, handling dependencies, and launching the embedded web server. |
| [ApiPizzaController.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\api\controller\ApiPizzaController.java)                            | This is the file dedicated to the api for the Pizza pojo. |
| [AuthConfig.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\conf\AuthConfig.java)                                                 | This is the file dedicated to the auth configuration |
| [Role.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\pojo\Role.java)                                                             | This is the Role pojo. |
| [User.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\pojo\User.java)                                                             | This is the User pojo. |
| [RoleRepo.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\repo\RoleRepo.java)                                                     | This is the repository for the Role pojo. |
| [UserRepo.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\repo\UserRepo.java)                                                     | This is the repository for the User pojo. |
| [RoleServ.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\services\RoleServ.java)                                                 | This is the service for the Role pojo. |
| [UserServ.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\auth\services\UserServ.java)                                                 | This is the repository for the User pojo. |
| [IngredientController.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\controllers\IngredientController.java)                           | This is the controller for the Ingredient pojo. |
| [MainController.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\controllers\MainController.java)                                       | This is the  generic controller for the back-end. |
| [PizzaController.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\controllers\PizzaController.java)                                     | This is the controller for the Pizza pojo. |
| [SpecialOfferController.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\controllers\SpecialOfferController.java)                       | This is the controller for the SpecialOffer pojo. |
| [Helper.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\helper\Helper.java)                                                            | This is the helper file containing the usefull functions. |
| [Ingredient.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\pojo\Ingredient.java)                                                      | This is the Ingredient pojo. |
| [Pizza.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\pojo\Pizza.java)                                                                | This is the Pizza pojo. |
| [SpecialOffer.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\pojo\SpecialOffer.java)                                                  | This is the SpecialOffer pojo. |
| [IngredientRepo.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\repo\IngredientRepo.java)                                              | This is the Ingredient pojo. |
| [PizzaRepo.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\repo\PizzaRepo.java)                                                        | This is the repository for the Pizza pojo. |
| [SpecialOfferRepo.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\repo\SpecialOfferRepo.java)                                          | This is the repository for the SpecialOffer pojo. |
| [IngredientServ.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\services\IngredientServ.java)                                          | This is the repository for the Ingredient pojo. |
| [PizzaServ.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\services\PizzaServ.java)                                                    | This is the service for the Pizza pojo. |
| [SpecialOfferServ.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\java\org\java\spring\services\SpecialOfferServ.java)                                      | This is the service for the SpecialOffer pojo. |
| [header.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\frag\header.html)                                                               | This is the fragment in the back-end dedicated to the header.  |
| [main-layout.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\frag\main-layout.html)                                                     | This is the fragment in the back-end which represents the main-layout with the generic info. |
| [index.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\index.html)                                                                 | This is the home for the back-end. |
| [index.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\ingredient\index.html)                                                      | This is the index file for the Ingredient pojo. |
| [trash.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\ingredient\trash.html)                                                      | This is the trash file for the Ingredient pojo. |
| [table-layout.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\ingredient\frag\table-layout.html)                                   | This is the fragment with the layout of the table for the index and trash files of the Ingredient pojo. |
| [create.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\create.html)                                                         | This is the create file for the Ingredient pojo. |
| [edit.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\edit.html)                                                             | This is the edit file for the Ingredient pojo. |
| [index.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\index.html)                                                           | This is the index file for the Pizza pojo. |
| [show.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\show.html)                                                             | This is the show file for the Pizza pojo. |
| [trash.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\trash.html)                                                           | This is the trash file for the Pizza pojo. |
| [form.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\frag\form.html)                                                        | This is the fragment with the layout of the form for the create and edit files of the Pizza pojo. |
| [table-layout.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\pizza\frag\table-layout.html)                                        | This is the fragment with the layout of the table for the index and trash files of the Pizza pojo. |
| [create.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\create.html)                                                 | This is the create file for the SpecialOffer pojo. |
| [edit.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\edit.html)                                                     | This is the edit file for the SpecialOffer pojo. |
| [index.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\index.html)                                                   | This is the index file for the SpecialOffer pojo. |
| [show.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\show.html)                                                     | This is the show file for the SpecialOffer pojo. |
| [trash.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\trash.html)                                                   | This is the trash file for the SpecialOffer pojo. |
| [form.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\frag\form.html)                                                | This is the fragment with the layout of the form for the create and edit files of the SpecialOffer pojo. |
| [table-layout.html](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\main\resources\templates\view\special-offer\frag\table-layout.html)                                | This is the fragment with the layout of the table for the index and trash files of the SpecialOffer pojo. |
| [SpringLaMiaPizzeriaCrudApplicationTests.java](https://github.com/AnielloPiscopo/spring-la-mia-pizzeria-webapi/blob/main/src\test\java\org\java\spring\SpringLaMiaPizzeriaCrudApplicationTests.java) | This file is responsible for orchestrating the execution of tests, including unit tests, integration tests, and end-to-end tests, to ensure the application's functionality is validated under various scenarios. |

</details>

---

## 🚀 Getting Started

### ✔️ Prerequisites

Before you begin, ensure that you have the following prerequisites installed:
> - `ℹ️ Java Development Kit (JDK): Ensure you have the Java Development Kit (JDK) installed on your system. You can download the latest JDK from the official Oracle website or from alternative sources like OpenJDK. `
> - `ℹ️ Code Editor or IDE: To open and edit the Java source code, it's helpful to have a code editor or an Integrated Development Environment (IDE) like Eclipse, IntelliJ IDEA, or Visual Studio Code.`
> - `ℹ️ Dependency Management (optional): Depending on the project, you may need to manage the program's dependencies. Typically, this is done using a dependency management tool like Maven or Gradle. Check if the project uses one of these tools and make sure you have them installed if necessary.`

### 📦 Installation

1. Clone the java-gestore-eventi repository:
```sh
git clone https://github.com/AnielloPiscopo/java-gestore-eventi
```

2. Change to the project directory:
```sh
cd java-gestore-eventi
```

---
