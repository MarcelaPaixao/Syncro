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
        <CardFeedback v-for="fb in feedbacks" :key="fb.id" :feedback="fb" />
      </div>

      <div class="new-feedback-input mt-auto pt-4">
        <textarea
          v-model="novoFeedback"
          placeholder="Digite aqui seu feedback..."
          class="w-full p-3 border border-gray-300 rounded-xl resize-none"
          rows="3"
        ></textarea>

        <div class="flex justify-end items-center mt-2 gap-3">
          <p class="text-xs text-gray-500 mr-auto">Aprovar tarefa?</p>

          <button
            @click="enviarFeedback(false)"
            :disabled="usuarioJaDeuFeedback"
            class="w-10 h-10 flex items-center justify-center bg-red-100 text-red-600 rounded-full hover:bg-red-200 text-2xl font-bold disabled:opacity-50 disabled:cursor-not-allowed"
          >
            &#10006;
          </button>

          <button
            @click="enviarFeedback(true)"
            :disabled="usuarioJaDeuFeedback"
            class="w-10 h-10 flex items-center justify-center bg-teal-100 text-teal-600 rounded-full hover:bg-teal-200 text-2xl font-bold disabled:opacity-50 disabled:cursor-not-allowed"
          >
            &#10004;
          </button>
        </div>
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
import CardFeedback from "@/components/CardFeedback.vue";

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
    CardFeedback,
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
      novoFeedback: "",
      feedbacks: [
        // MARCELA: simulação; pegar dados do backend
        {
          id: 1,
          membro: { id: 1, nome: "Belinha" },
          texto: "amei amei",
          aprovacao: true,
        },
        {
          id: 2,
          membro: { id: 2, nome: "Caramelo" },
          texto: "horrivel :(",
          aprovacao: false,
        },
      ],
      usuarioAtual: { id: 99, nome: "Fulano" }, //MARCELA: simulação; pegar usuario atual do backend
      estadosPossiveis: ["TODO", "DOING", "REVIEW", "DONE"],
    };
  },

  computed: {
    usuarioJaDeuFeedback() {
      return this.feedbacks.some((fb) => fb.membro.id === this.usuarioAtual.id);
    },
  },

  methods: {
    enviarFeedback(feedbackAprovacao) {
      const textoFeedback = this.novoFeedback.trim();

      if (textoFeedback === "" && feedbackAprovacao === null) return;

      //MARCELA: id simulado; pegar do backend
      const novoFeedbackObj = {
        id: Date.now(),
        membro: this.usuarioAtual,
        texto: textoFeedback,
        aprovacao: feedbackAprovacao, // 'true' ou 'false'
      };

      this.feedbacks.push(novoFeedbackObj);
      console.log("Enviando novo feedback:", novoFeedbackObj);

      this.novoFeedback = "";
    },

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
