<template>
  <AppHeader />
  <div class="p-8 space-y-4">
    <h1 class="text-3xl font-bold text-gray-800">Dashboard</h1>

    <div class="modal-tarefa">
      <BotaoCustomizado
        @click="isModalTarefaVisible = true"
        texto="Abrir Modal de Tarefas"
      />
      <CriarTarefaModal
        :visivel="isModalTarefaVisible"
        :membrosDoGrupo="listaMembrosGrupo"
        @close="isModalTarefaVisible = false"
        @salvar="adicionarTarefaAoGrupo"
      />
    </div>

    <div class="modal-descricao">
      <BotaoCustomizado
        @click="isModalDescricaoVisible = true"
        texto="Abrir Modal de Descricao"
      />
      <VerDescricaoModal
        :visivel="isModalDescricaoVisible"
        @close="isModalDescricaoVisible = false"
      />
    </div>

    <div class="modal-membros">
      <BotaoCustomizado
        @click="isModalMembrosVisible = true"
        texto="Abrir Modal de Membros"
      />
      <VerMembrosModal
        :visivel="isModalMembrosVisible"
        @close="isModalMembrosVisible = false"
      />
    </div>
  </div>
</template>

<script>
import CriarTarefaModal from "@/components/CriarTarefaModal.vue";
import VerDescricaoModal from "@/components/VerDescricaoModal.vue";
import VerMembrosModal from "@/components/VerMembrosModal.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  components: {
    CriarTarefaModal,
    BotaoCustomizado,
    AppHeader,
    VerDescricaoModal,
    VerMembrosModal,
  },
  data() {
    return {
      isModalTarefaVisible: false,
      isModalDescricaoVisible: false,
      isModalMembrosVisible: false,
      listaMembrosGrupo: [
        { id: 1, nome: "Marcela" },
        { id: 2, nome: "Malu" },
        { id: 3, nome: "Fulano" },
      ],
    };
  },
  methods: {
    adicionarTarefaAoGrupo(dadosDaTarefa) {
      console.log("Tarefa recebida:", dadosDaTarefa);
      // aqui coloca a tarefa no grupo com chamada ao backend
      this.isModalTarefaVisible = false;
    },
  },
};
</script>
