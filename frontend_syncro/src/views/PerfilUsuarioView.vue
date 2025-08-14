<template>
  <div>
    <AppHeader />
    <IconePerfil :nome="nomeUsuario" />
    <div class="p-8">
      <h1 class="text-2xl font-bold text-center">{{ nomeUsuario }}</h1>
    </div>

    <div class="grid grid-cols-3 gap-9 px-6">
      <CardGrupos
        titulo="Meus Grupos"
        :tarefas="gruposUsuario"
        @informaCriarGrupo="redirecionaCriarGrupo"
      />
      <CardTarefas titulo="Minhas Tarefas" :tarefas="tarefasUsuarios" />
      <CardTarefas titulo="Para Revisar" :tarefas="tarefasRevisao" />
    </div>
  </div>
</template>

<script>
import AppHeader from "@/components/AppHeader.vue";
import CardTarefas from "@/components/CardTarefas.vue";
import CardGrupos from "@/components/CardGrupos.vue";
import IconePerfil from "@/components/IconePerfil.vue";

export default {
  name: "PerfilUsuarioView",
  components: {
    AppHeader,
    CardTarefas,
    CardGrupos,
    IconePerfil,
  },
  data() {
    return {
      gruposUsuario: [],
      tarefasUsuarios: [],
      tarefasRevisao: [],
      nomeUsuario: "Marcela",
    };
  },
  methods: {
    redirecionaCriarGrupo() {
      this.$router.push("/criar-grupo");
    },
  },
};
</script>
