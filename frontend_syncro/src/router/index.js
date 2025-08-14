import { createRouter, createWebHistory } from "vue-router";
// import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import CadastroView from "../views/CadastroView.vue";
import EsqueceuSuaSenhaView from "../views/EsqueceuSuaSenhaView.vue";
import RedefinirSenhaView from "../views/RedefinirSenhaView.vue";
import CriarNovoGrupoView from "@/views/CriarNovoGrupoView.vue";
import PerfilUsuarioView from "@/views/PerfilUsuarioView.vue";
import DashboardView from "@/views/DashboardView.vue";
import VisualizarTarefaView from "@/views/VisualizarTarefaView.vue";
import { getAccessToken } from "axios-jwt";

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
    meta: { requiresAuth: false },
  },
  {
    path: "/cadastro",
    name: "cadastro",
    component: CadastroView,
    meta: { requiresAuth: false },
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
    meta: { requiresAuth: true },
    component: CriarNovoGrupoView,
  },
  {
    path: "/perfil-usuario",
    name: "perfil-usuario",
    meta: { requiresAuth: true },
    component: PerfilUsuarioView,
  },
  {
    path: "/visualizar-tarefa",
    name: "visualizar-tarefa",
    component: VisualizarTarefaView,
  },
  {
    path: "/criar-tarefa",
    name: "criar-tarefa",
    meta: { requiresAuth: true },
    component: DashboardView,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);
  const isAuthenticated = !!getAccessToken();
  console.log(requiresAuth);
  console.log(isAuthenticated);
  if (requiresAuth && !isAuthenticated) {
    next("/login");
  } else {
    next();
  }
});

export default router;
