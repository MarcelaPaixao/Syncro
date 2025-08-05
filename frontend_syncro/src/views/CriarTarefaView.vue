<template>
  <AppHeader />
  <h2>Nova Tarefa</h2>

  <form @submit.prevent="criarTarefa">
    <div class="nova-tarefa">
      <div class="input-container">
        <div class="input-group">
          <label>Título</label>
          <input
            type="text"
            v-model="titulo"
            required
            placeholder="Digite algo..."
          />
        </div>
        <div class="input-group">
          <label>Descrição</label>
          <textarea
            v-model="descricao"
            maxlength="300"
            placeholder="Digite algo..."
          ></textarea>
        </div>
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
          <label>Data</label>
          <input type="date" v-model="prazoTarefa" required />
        </div>
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
      <BotaoCustomizado type="submit" texto="Salvar" id="criar-tarefa-btn" />
    </div>
  </form>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
export default {
  name: "CriarTarefaView",
  components: {
    BotaoCustomizado,
    AppHeader,
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
      links: [], //conferir em relação aos links, tentar colocar dinamicamente campos de input
    };
  },
  methods: {
    criarTarefa() {
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
input {
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  width: 75%;
}

.nova-tarefa {
  display: flex;
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

textarea {
  resize: none;
  width: 75%;
  height: 150px;
  border-radius: 10px;
  border: 0.1px solid rgb(205, 213, 217);
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
  background-color: rgb(216, 226, 225);
  box-shadow: 4px 6px 6px rgba(0, 0, 0, 0.173);
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
