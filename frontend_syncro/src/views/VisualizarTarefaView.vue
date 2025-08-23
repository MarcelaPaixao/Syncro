<!-- Ajustar arquivo, pois não serão inputs normais, mas a visualização dos dados da tarefa
 e possibilidade de alteração -->
<template>
  <AppHeader />
  <h2 class="text-2xl font-bold text-gray-800 text-center my-4">
    Perfil da Tarefa
  </h2>

  <div class="visualizar-tarefa grid grid-cols-[2fr_1.1fr] gap-x-10 p-8">
    <div class="text-group flex flex-col min-w-0">
      <form @submit.prevent="visualizarTarefa" class="flex flex-col flex-grow">
        <div class="info-container space-y-4">
          <InputString v-model="titulo" label="Título" />
          <TextArea v-model="descricao" label="Descrição" />

          <div>
            <label class="block mb-1 text-base font-bold text-gray-700"
              >Responsável</label
            >
            <select
              v-model="idDoMembroResponsavel"
              id="idDoMembroResponsavel"
              required
              class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500"
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

          <InputString v-model="prazoTarefa" label="Prazo" type="date" />

          <div>
            <label class="block mb-1 text-base font-bold text-gray-700"
              >Estado</label
            >
            <select
              v-model="status"
              class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500"
              required
            >
              <option value="" disabled>Selecione o estado da Tarefa</option>
              <option
                v-for="(estado, index) in estadosPossiveis"
                :key="index"
                :value="estado"
                this.estadoTarefa="estado"
              >
                {{ estado }}
              </option>
            </select>
          </div>
          <InputString v-model="linkExtra" label="Link Extra" />
        </div>

        <div class="btn-container mt-auto pt-4">
          <BotaoCustomizado type="submit" texto="Salvar" @click="editaTarefa" />
        </div>
      </form>
    </div>

    <div
      class="feedback-container flex flex-col gap-6 pl-10 border-l border-gray-200"
    >
      <div class="feedback-header flex justify-between items-center">
        <h3 class="text-2xl font-bold">Feedbacks</h3>
        <a href="#" class="text-gray-600 hover:text-black no-underline"
          >Filtrar &#9662;</a
        >
      </div>

      <div class="feedback-list flex-grow overflow-y-auto space-y-4">
        <!-- <FeedbackCard v-for="fb in feedbacks" :key="fb.id" :feedback="fb" /> -->
      </div>

      <div class="new-feedback-input relative">
        <input
          type="text"
          placeholder="Digite aqui seu feedback"
          class="w-full p-3 pr-10 border border-gray-300 rounded-xl"
        />
      </div>
    </div>
  </div>
</template>
<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import InputString from "@/components/InputString.vue";
import TextArea from "@/components/TextArea.vue";
import { editaTarefa, getTarefaById } from "@/services/tarefaService";
import { getAlunosGrupo } from "@/services/alunoService";

export default {
  props: {
    tarefaId: {
      type: [String, Number], // Aceita String da URL ou Número
      required: true,
    },
  },
  name: "VisualizarTarefaView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
  },
  data() {
    return {
      idDoMembroResponsavel: null,
      grupoId: null,
      titulo: "",
      descricao: "",
      prazoTarefa: "",
      status: "",
      membrosDoGrupo: [],
      linkDrive: "",
      linkExtra: "",
      feedbacks: [],
      estadosPossiveis: ["TODO", "DOING", "REVIEW", "DONE"],
    };
  },
  methods: {
    async editaTarefa() {
      const createTarefaDTO = {
        id: this.tarefaId,
        titulo: this.titulo,
        descricao: this.descricao,
        prazo: this.prazoTarefa,
        status: this.status,
        linkDrive: this.linkDrive,
        linkExtra: this.linkExtra,
        alunoId: this.idDoMembroResponsavel,
        grupoId: this.grupoId,
      };
      await editaTarefa(createTarefaDTO);
      console.log(createTarefaDTO);
    },
  },
  async mounted() {
    try {
      const tarefa = await getTarefaById(this.tarefaId);
      this.grupoId = tarefa.grupoId;
      this.status = tarefa.status;
      console.log(tarefa);
      if (tarefa) {
        this.titulo = tarefa.titulo;
        this.descricao = tarefa.descricao ?? "adicione uma descrição";
        this.prazoTarefa = tarefa.prazo;
        this.status = tarefa.status;
        this.linkDrive = tarefa.linkDrive ?? "";
        this.linkExtra = tarefa.linkExtra ?? "";
        this.membrosDoGrupo = await getAlunosGrupo(tarefa.grupoId);
        this.idDoMembroResponsavel = tarefa.alunoId;
      }
    } catch (error) {
      console.error("Falha ao carregar dados da tarefa:", error);
    }
  },
};
</script>
