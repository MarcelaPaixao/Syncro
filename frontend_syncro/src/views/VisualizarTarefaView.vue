<template>
  <AppHeader />
  <h2>Visualizar Tarefa</h2>

  <form @submit.prevent="visualizarTarefa">
    <div class="visualizar-tarefa">
      <div class="input-container">
        <div class="input-group">
          <InputString v-model="titulo" label="Título" />
        </div>
        <div class="input-group">
          <TextArea v-model="descricao" label="Descrição"></TextArea>
        </div>
        <!-- MARCELA: Fazer componente especifico -->
        <div class="input-group">
          <label>Responsável</label>
          <select v-model="responsavel" required>
            <option value="">Selecione um membro</option>
            <option
              v-for="membro in membrosDoGrupo"
              :key="membro.id"
              :value="membro.id"
            >
              {{ membro.nome }}
            </option>
          </select>
        </div>
        <div class="input-group">
          <InputString v-model="prazoTarefa" label="Prazo" type="date" />
        </div>
        <!-- MARCELA:Conferir se aqui precisa de componente especifico ou se InputString vale -->
        <div class="input-group">
          <label>Links</label>
          <input type="text" v-model="links" />
        </div>
      </div>

      <div class="feedback-container">
        <div class="box">
          <label>Feedbacks</label>
        </div>
      </div>
    </div>
    <div class="btn-container">
      <BotaoCustomizado
        type="submit"
        texto="Salvar"
        id="visualizar-tarefa-btn"
      />
    </div>
  </form>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import InputString from "@/components/InputString.vue";
import TextArea from "@/components/TextArea.vue";

export default {
  name: "visualizarTarefaView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
  },
  data() {
    return {
      titulo: "",
      descricao: "",
      responsavel: "",
      prazoTarefa: "",
      status: "",
      membrosDoGrupo: [
        { id: 1, nome: "Marcela" },
        { id: 2, nome: "Malu" },
        { id: 3, nome: "Fulano" },
      ],
      links: [], //MARCELA:conferir em relação aos links, tentar colocar dinamicamente campos de input
    };
  },
  methods: {
    visualizarTarefa() {
      console.log("Dados do grupo:", {
        titulo: this.titulo,
        descricao: this.descricao,
        responsavel: this.responsavel,
        prazoTarefa: this.prazoTarefa,
        status: this.status,
        links: this.links,
        membrosDoGrupo: this.membrosDoGrupo,
      });
    },
  },
};
</script>

<style scoped>
input,
.input-group :deep(input) {
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  width: 75%;
}
.input-group {
  width: 100%;
}
.nova-tarefa {
  display: flex;
  width: 100vw;
  height: 100vh;
}

.input-container {
  width: 65%;
  margin-left: 20px;
}

.feedback-container {
  /* justify-content: center; */
  padding-left: 1rem;
  border-left: 1px solid black;
}

.btn-container {
  display: flex;
  justify-content: flex-start;
  padding: 1rem 0;
}

.input-group :deep(.textarea-container) {
  width: 75%;
  height: 150px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  border-radius: 10px;
  display: block; /* Garante o comportamento correto */
}

.input-group :deep(textarea) {
  height: 100%; /* Faz a textarea preencher a altura do contêiner */
}

select {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border-radius: 10px;
  border: 0.1px solid rgb(205, 213, 217);
  padding: 0.6rem;
  width: 75%;
}
</style>

<!-- <style scoped>
input,
.input-group :deep(input) {
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  width: 75%;
}
.input-group {
  width: 100%;
}
.nova-tarefa {
  display: flex;
  width: 100vw;
  height: 100vh;
}

.input-container {
  width: 65%;
  margin-left: 20px;
}

.feedback-container {
  /* justify-content: center; */
  padding-left: 1rem;
  border-left: 1px solid black;
}

.btn-container {
  display: flex;
  justify-content: flex-start;
  padding: 1rem 0;
}

.input-group :deep(.textarea-container) {
  width: 75%;
  height: 150px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  border-radius: 10px;
  display: block; /* Garante o comportamento correto */
}

.input-group :deep(textarea) {
  height: 100%; /* Faz a textarea preencher a altura do contêiner */
}

select {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border-radius: 10px;
  border: 0.1px solid rgb(205, 213, 217);
  padding: 0.6rem;
  width: 75%;
}
</style> -->
