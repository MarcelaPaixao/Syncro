<template>
  <AppHeader />
  <h2>Novo Projeto</h2>

  <form @submit.prevent="criarGrupo">
    <div class="novo-grupo">
      <div class="input-container">
        <div class="form-grid">
          <div class="input-group">
            <label>Nome</label>
            <input
              type="text"
              v-model="name"
              required
              placeholder="Digite algo..."
            />
          </div>
          <div class="input-group">
            <label>Matéria</label>
            <input
              type="text"
              v-model="materia"
              required
              placeholder="Digite algo..."
            />
          </div>
          <div class="input-group">
            <label>Professor</label>
            <input
              type="text"
              v-model="professor"
              required
              placeholder="Digite algo..."
            />
          </div>
          <div class="input-group">
            <label>Data</label>
            <input type="date" v-model="prazo" />
          </div>
        </div>

        <div class="descricao-container">
          <label>Descrição</label>
          <textarea
            v-model="descricao"
            maxlength="300"
            placeholder="Digite algo..."
          ></textarea>
        </div>
      </div>

      <div class="checklist-container">
        <div class="box">
          <label>Membros</label>
        </div>
      </div>
    </div>
    <div class="btn-container">
      <BotaoCustomizado type="submit" texto="Salvar" id="criar-grupo-btn" />
    </div>
  </form>
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
input {
  border: 2px solid #077a7d;
  font-size: medium;
}

.novo-grupo {
  display: flex;
}

.form-grid {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
}

.form-grid .input-group {
  flex-basis: calc(50% - 0.9rem);
}

.input-container {
  width: 65%;
  margin-left: 20px;
}

.checklist-container {
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
  width: 98%;
  height: 200px;
  border-radius: 10px;
  border: 2px solid #077a7d;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: medium;
}
</style>
