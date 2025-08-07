<template>
  <AppHeader />
  <h2>Novo Projeto</h2>

  <form @submit.prevent="criarGrupo">
    <div class="novo-grupo">
      <div class="text-container">
        <div class="input-container">
          <div class="input-group">
            <InputString v-model="name" label="Nome" />
          </div>
          <div class="input-group">
            <InputString v-model="materia" label="Matéria" />
          </div>
          <div class="input-group">
            <InputString v-model="professor" label="Professor" />
          </div>
          <div class="input-group">
            <InputString v-model="prazo" label="Data" type="date" />
          </div>
        </div>

        <div class="descricao-container">
          <TextArea v-model="descricao" label="Descrição"></TextArea>
        </div>
      </div>

      <div class="checklist-container">
        <div>
          <label>Membros</label>
        </div>
      </div>
      <div class="btn-container">
        <BotaoCustomizado type="submit" texto="Salvar" id="criar-grupo-btn" />
      </div>
    </div>
  </form>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import InputString from "@/components/InputString.vue";
import TextArea from "@/components/TextArea.vue";

export default {
  name: "CriarNovoGrupoView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
  },
  data() {
    return {
      name: "",
      materia: "",
      professor: "",
      prazo: "",
      descricao: "",
      membros: [],
      // membrosError: "",
    };
  },
  methods: {
    criarGrupo() {
      // this.membrosError="";

      // if(this.membros.length < 2) {
      //   this.membrosError="Não há membros o suficiente!";
      //   return;
      // }

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

<style scoped>
/* .input-group :deep(input) {
  border: 2px solid #077a7d;
  font-size: medium;
} */

.novo-grupo {
  display: grid;
  width: 100vw;
  height: 100vh;
  grid-template-columns: 2fr 1.1fr;
  /* grid-template-rows: 7fr 1fr; */
  column-gap: 40px;
  padding-left: 30px;
  padding-right: 30px;
}

.text-container {
  display: grid;
  align-items: stretch;
}

.input-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  column-gap: 30px;
}

.descricao-container:deep(.textarea-container) {
  height: 250px;
  border: 1.55px solid #dfe0da;
  border-radius: 20px;
  display: block;
}

.descricao-container:deep(textarea) {
  height: 100%;
  background-color: transparent;
}

.btn-container {
  display: grid;
  justify-content: left;
}

.checklist-container {
  display: grid;
  border-left: 1px solid black;
  justify-content: center;
}

.input-group :deep(input),
.descricao-container :deep(textarea) {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
}
</style>
