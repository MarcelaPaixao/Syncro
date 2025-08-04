import { createRouter, createWebHistory } from "vue-router";
// import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import CadastroView from "../views/CadastroView.vue";
import EsqueceuSuaSenhaView from "../views/EsqueceuSuaSenhaView.vue";
import RedefinirSenhaView from "../views/RedefinirSenhaView.vue";
import CriarNovoGrupoView from "@/views/CriarNovoGrupoView.vue";
import PerfilUsuarioView from "@/views/PerfilUsuarioView.vue";
import CriarTarefaView from "@/views/CriarTarefaView.vue";

const routes = [
  // {
  //   path: "/",
  //   name: "home",
  //   component: HomeView,
  // },
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/cadastro",
    name: "cadastro",
    component: CadastroView,
  },
  {
    path: "/esqueceu-sua-senha",
    name: "esqueceu-sua-senha",
    component: EsqueceuSuaSenhaView,
  },
  {
    path: "/redefinir-senha",
    name: "redefinir-senha",
    component: RedefinirSenhaView,
  },
  {
    path: "/criar-grupo",
    name: "criar-grupo",
    component: CriarNovoGrupoView,
  },
  {
    path: "/perfil-usuario",
    name: "perfil-usuario",
    component: PerfilUsuarioView,
  },
  {
    path: "/criar-tarefa",
    name: "criar-tarefa",
    component: CriarTarefaView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
