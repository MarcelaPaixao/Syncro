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
        :grupos="gruposUsuario"
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
import { getGruposAluno } from "@/services/grupoService";
import { getUser } from "@/services/alunoService";
import {
  getTarefasAluno,
  getTarefasAlunoAvaliar,
} from "@/services/tarefaService";

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
      nomeUsuario: "",
      user: [],
    };
  },
  methods: {
    redirecionaCriarGrupo() {
      this.$router.push("/criar-grupo");
    },
    async getGrupos() {
      this.gruposUsuario = await getGruposAluno();
    },
    async getTarefas() {
      this.tarefasUsuarios = await getTarefasAluno(this.user.id);
    },
    async getTarefasAvaliacao() {
      this.tarefasRevisao = await getTarefasAlunoAvaliar(this.user.id);
    },
  },
  async mounted() {
    this.user = await getUser();
    this.nomeUsuario = this.user.nome;
    if (this.user.id != undefined && this.user.id != null) {
      await this.getGrupos();
      await this.getTarefas();
      await this.getTarefasAvaliacao();
    }
  },
};
</script>
