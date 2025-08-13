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
              v-model="responsavel"
              required
              class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500"
            >
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

          <InputString v-model="prazoTarefa" label="Prazo" type="date" />

          <div class="status-group">
            <label class="block mb-1 text-base font-bold text-gray-700"
              >Status</label
            >
            <p
              class="w-full px-3 py-2 bg-gray-100 border border-gray-300 rounded-xl italic text-gray-600"
            >
              {{ status }}
            </p>
          </div>

          <div>
            <label class="block mb-1 text-base font-bold text-gray-700"
              >Links</label
            >
            <TagInputVertical
              v-model="links"
              type="url"
              placeholder="Cole um link e pressione Enter"
              :itemsAsLinks="true"
            />
          </div>
        </div>

        <div class="btn-container mt-auto pt-4">
          <BotaoCustomizado type="submit" texto="Salvar" />
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
import TagInputVertical from "@/components/TagInputVertical.vue";

export default {
  name: "VisualizarTarefaView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
    TagInputVertical,
  },
  data() {
    return {
      titulo: "",
      descricao: "",
      responsavel: "",
      prazoTarefa: "",
      status: "Aguardando aprovação (x/x)",
      membrosDoGrupo: [
        { id: 1, nome: "Marcela" },
        { id: 2, nome: "Malu" },
        { id: 3, nome: "Caramelo" },
      ],
      links: [],
      feedbacks: [],
    };
  },
  //Marcela: criado para simulação
  created() {
    const tarefaId = parseInt(this.$route.params.id);
    const dadosDoBanco = this.buscarTarefaNoBanco(tarefaId);
    if (dadosDoBanco) {
      this.titulo = dadosDoBanco.titulo;
      this.descricao = dadosDoBanco.descricao;
      this.responsavel = dadosDoBanco.responsavel;
      this.prazoTarefa = dadosDoBanco.prazoTarefa;
    } else {
      console.error("Tarefa não encontrada!");
    }
  },
  methods: {
    //Marcela: criado para simulação
    buscarTarefaNoBanco(id) {
      const todasAsTarefas = [
        { id: 1691945189000, titulo: "Tarefa A", descricao: "Descrição A" },
        { id: 1691945199000, titulo: "Tarefa B", descricao: "Descrição B" },
      ];
      return todasAsTarefas.find((tarefa) => tarefa.id === id);
    },

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
