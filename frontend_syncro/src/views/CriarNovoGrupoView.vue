<template>
  <AppHeader />
  <h2 class="text-2xl font-bold text-gray-800 text-center my-4">
    Novo Projeto
  </h2>

  <form
    @submit.prevent="criarGrupo"
    class="grid grid-cols-[2fr_1.1fr] gap-x-10 p-8"
  >
    <div class="flex flex-col gap-y-6">
      <div class="grid grid-cols-2 gap-x-8 gap-y-4">
        <InputString v-model="name" label="Nome" />
        <InputString v-model="materia" label="Matéria" />
        <InputString v-model="professor" label="Professor" />
        <InputString v-model="prazo" label="Data" type="date" />
      </div>

      <div>
        <TextArea v-model="descricao" label="Descrição" />
      </div>

      <div>
        <label class="text-base font-bold text-gray-700 mb-2 block"
          >Tarefas</label
        >
        <div class="flex flex-wrap items-center gap-x-4 gap-y-2">
          <router-link
            v-for="(tarefa, index) in tarefas"
            :key="index"
            :to="'tarefa/${tarefa.id}'"
            class="text-teal-600 underline cursor-pointer hover:text-teal-800"
          >
            {{ tarefa.titulo }}
          </router-link>

          <button
            @click="abrirModalTarefas"
            type="button"
            class="flex items-center justify-center w-8 h-8 bg-gray-200 rounded-full hover:bg-gray-300 transition-colors"
          >
            <span class="text-2xl font-bold text-gray-600 leading-none mb-1"
              >+</span
            >
          </button>
        </div>
      </div>

      <div class="mt-auto">
        <BotaoCustomizado type="submit" texto="Salvar" />
      </div>
    </div>

    <div
      class="checklist-container border-l border-gray-300 pl-10 flex flex-col min-w-0"
    >
      <label class="text-lg font-bold text-gray-700 mb-4">Membros</label>
      <TagInputVertical
        v-model="membrosEmail"
        type="email"
        placeholder="Digite um email e pressione Enter"
        @interaction="limpaMembrosError"
      />
      <p v-if="membrosError" class="text-sm text-red-600 italic mt-2">
        {{ membrosError }}
      </p>
    </div>
  </form>

  <CriarTarefaModal
    :visivel="isModalTarefaVisible"
    :membrosDoGrupo="membrosProvisorio"
    @close="isModalTarefaVisible = false"
    @salvar="adicionarTarefa"
  />
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import InputString from "@/components/InputString.vue";
import TextArea from "@/components/TextArea.vue";
import TagInputVertical from "@/components/TagInputVertical.vue";
import CriarTarefaModal from "@/components/CriarTarefaModal.vue";

export default {
  name: "CriarNovoGrupoView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
    TagInputVertical,
    CriarTarefaModal,
  },
  data() {
    return {
      name: "",
      materia: "",
      professor: "",
      prazo: "",
      descricao: "",
      membrosEmail: [],
      //Marcela: criado para simulação
      membrosProvisorio: [
        { id: 1, nome: "Marcela" },
        { id: 2, nome: "Malu" },
        { id: 3, nome: "Caramelo" },
      ],
      membrosError: "",
      isModalTarefaVisible: false,
      tarefas: [],
    };
  },
  methods: {
    limpaMembrosError() {
      this.membrosError = "";
    },

    criarGrupo() {
      if (this.membrosEmail && this.membrosEmail.length < 2) {
        this.membrosError = "Não há membros o suficiente!";
        return;
      }

      console.log("Dados do grupo:", {
        name: this.name,
        materia: this.materia,
        professor: this.professor,
        prazo: this.prazo,
        descricao: this.descricao,
      });
    },

    abrirModalTarefas() {
      this.isModalTarefaVisible = true;
    },

    //Marcela: criado para simulação
    adicionarTarefa(dadosDaTarefa) {
      const novaTarefa = {
        ...dadosDaTarefa,
        id: Date.now(),
      };
      this.tarefas.push(novaTarefa);
      this.isModalTarefaVisible = false;
    },
  },
};
</script>
