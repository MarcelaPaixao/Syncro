<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2 class="text-2xl font-bold text-gray-800">Nova Tarefa</h2>
    </template>

    <form id="form-tarefa" @submit.prevent="submitTarefa" class="space-y-4">
      <div class="grid grid-cols-2 gap-5">
        <InputString v-model="tarefaLocal.titulo" label="Título" />
        <InputString
          v-model="tarefaLocal.prazoTarefa"
          label="Prazo"
          type="date"
        />
      </div>

      <TextArea v-model="tarefaLocal.descricao" label="Descrição" />

      <div>
        <label class="block mb-1 text-base font-bold text-gray-700"
          >Responsável</label
        >
        <select
          v-model="tarefaLocal.responsavel"
          required
          class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500"
        >
          <option value="" disabled>Selecione um membro</option>
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
      console.log("Dados recebidos:", {
        tarefaLocal: this.tarefaLocal,
      });

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
