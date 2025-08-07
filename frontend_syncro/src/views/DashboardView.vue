<template>
  <div>
    <button @click="isModalVisible = true">Abrir Modal de Tarefas</button>
    <CriarTarefaModal
      :visivel="isModalVisible"
      :membrosDoGrupo="listaMembrosGrupo"
      @close="isModalVisible = false"
      @salvar="adicionarTarefaAoGrupo"
    />
  </div>
</template>

<script>
import CriarTarefaModal from "@/components/CriarTarefaModal.vue";

export default {
  components: {
    CriarTarefaModal,
  },
  data() {
    return {
      isModalVisible: false,
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
      this.isModalVisible = false;
    },
  },
};
</script>
