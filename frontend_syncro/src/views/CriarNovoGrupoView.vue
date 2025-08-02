<template>
  <AppHeader />
  <div class="novo-grupo">
    <form @submit.prevent="criarGrupo">
      <div class="input-group">
        <label>Nome</label>
        <input type="text" v-model="name" required />
      </div>
      <div class="input-group">
        <label>Matéria</label>
        <input type="text" v-model="materia" required />
      </div>
      <div class="input-group">
        <label>Professor</label>
        <input type="text" v-model="professor" required />
      </div>
      <div class="input-group">
        <label>Data</label>
        <input type="datetime-local" v-model="prazo" />
      </div>
      <div class="input-group">
        <label>Descrição</label>
        <input type="text" v-model="descricao" />
      </div>
      <!-- <div class="input-group">
        <label>Membros</label>
      </div> -->

      <BotaoCustomizado type="submit" texto="Salvar" />
    </form>
  </div>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
export default {
  name: "CriarNovoGrupoView",
  components: {
    BotaoCustomizado,
    AppHeader,
  },
  data() {
    return {
      name: "",
      materia: "",
      professor: "",
      prazo: "",
      descricao: "",
    };
  },
  methods: {
    criarGrupo() {
      console.log("Dados do grupo:", {
        name: this.name,
        materia: this.materia,
        professor: this.professor,
        prazo: this.prazo,
        descricao: this.descricao,
      });
    },
  },
};
</script>

<style scoped></style>
