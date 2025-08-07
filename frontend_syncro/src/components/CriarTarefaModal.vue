<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2>Nova Tarefa</h2>
    </template>

    <form id="form-tarefa" @submit.prevent="submitTarefa">
      <div class="input-container">
        <div class="input-group">
          <InputString v-model="tarefaLocal.titulo" label="Título" />
        </div>
        <div class="input-group">
          <InputString
            v-model="tarefaLocal.prazoTarefa"
            label="Prazo"
            type="date"
          />
        </div>
      </div>
      <div class="input-group" id="descricao-box">
        <TextArea v-model="tarefaLocal.descricao" label="Descrição"></TextArea>
      </div>
      <!-- MARCELA: Fazer componente especifico -->
      <div class="input-group" id="responsavel-box">
        <label>Responsável</label>
        <select v-model="tarefaLocal.responsavel" required>
          <option value="" disabled selected hidden>Selecione um membro</option>
          <option
            v-for="membro in membrosDoGrupo"
            :key="membro.id"
            :value="membro.id"
          >
            {{ membro.nome }}
          </option>
        </select>
      </div>
    </form>

    <template v-slot:footer>
      <BotaoCustomizado type="submit" form="form-tarefa" texto="Salvar" />
    </template>
  </BaseModal>
</template>

<script>
import BotaoCustomizado from "./BotaoCustomizado.vue";
import InputString from "./InputString.vue";
import TextArea from "./TextArea.vue";
import BaseModal from "./BaseModal.vue";

export default {
  name: "CriarTarefaModal",
  components: {
    BotaoCustomizado,
    InputString,
    TextArea,
    BaseModal,
  },
  props: {
    visivel: { type: Boolean, required: true },
    membrosDoGrupo: { type: Array, required: true },
  },
  emits: ["close", "salvar"],
  data() {
    return {
      tarefaLocal: {
        titulo: "",
        descricao: "",
        responsavel: "",
        prazoTarefa: "",
      },
    };
  },
  methods: {
    submitTarefa() {
      this.$emit("salvar", this.tarefaLocal);

      this.tarefaLocal = {
        titulo: "",
        descricao: "",
        responsavel: "",
        prazoTarefa: "",
      };
    },
  },
};
</script>

<style scoped>
.input-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 20px;
  box-sizing: border-box;
}

.input-group :deep(input) {
  box-shadow: 4px 4px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
}

.input-group :deep(.textarea-container) {
  border-radius: 20px;
  height: 150px;
  box-shadow: 4px 4px 6px rgba(0, 0, 0, 0.173);
  border: 0.1px solid rgb(205, 213, 217);
  display: block;
}

.input-group :deep(textarea) {
  height: 100%;
}

#responsavel-box {
  margin-top: 20px;
  margin-bottom: 20px;
}

select {
  box-shadow: 4px 4px 6px rgba(0, 0, 0, 0.173);
  border-radius: 10px;
  border: 0.1px solid rgb(205, 213, 217);
  padding: 0.6rem;
  width: 100%;
}

.input-group :deep(input),
select,
.input-group :deep(textarea) {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 15px;
}
</style>
