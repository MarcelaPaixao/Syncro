<template>
  <div>
    <AppHeader />
    <SubBar
      @abrirModalMembros="isModalMembrosVisible = true"
      @abrirModalDescricao="isModalDescricaoVisible = true"
    />

    <div class="p-8">
      <h1 class="text-2xl font-bold text-center">Nome do grupo</h1>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 px-6">
      <CardTarefas
        v-for="coluna in colunas"
        :key="coluna.id"
        :titulo="coluna.titulo"
        :tarefas="coluna.tarefas"
        @abrirModalParaColuna="abrirModalTarefa(coluna.titulo)"
        :showAddButton="coluna.titulo === 'To-do'"
      />
    </div>

    <CriarTarefaModal
      :visivel="isModalTarefaVisible"
      :membrosDoGrupo="listaMembrosGrupo"
      @close="isModalTarefaVisible = false"
      @salvar="adicionarTarefaAoGrupo"
    />

    <VerMembrosModal
      :visivel="isModalMembrosVisible"
      :membrosDoGrupo="listaMembrosGrupo"
      @close="isModalMembrosVisible = false"
    />

    <VerDescricaoModal
      :visivel="isModalDescricaoVisible"
      :descricao="descricaoAtual"
      @close="isModalDescricaoVisible = false"
    />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from "vue";
import CriarTarefaModal from "@/components/CriarTarefaModal.vue";
import VerDescricaoModal from "@/components/VerDescricaoModal.vue";
import VerMembrosModal from "@/components/VerMembrosModal.vue";
import AppHeader from "@/components/AppHeader.vue";
import SubBar from "@/components/SubBar.vue";
import CardTarefas from "@/components/CardTarefas.vue";

const isModalTarefaVisible = ref(false);
const isModalDescricaoVisible = ref(false);
const isModalMembrosVisible = ref(false);

const listaMembrosGrupo = ref([
  { id: 1, nome: "Marcela", email: "marcela@a.com" },
  { id: 2, nome: "Malu", email: "malu@a.com" },
  { id: 3, nome: "Caramelo", email: "melinho@a.com" },
  { id: 4, nome: "Belinha", email: "belinha@a.com" },
  { id: 5, nome: "Fulano", email: "fulano@a.com" },
]);

const descricaoAtual = ref("exemploexemploexemplo");

const colunas = reactive({
  "To-do": { id: 1, titulo: "To-do", tarefas: [] },
  Doing: { id: 2, titulo: "Doing", tarefas: [] },
  Review: { id: 3, titulo: "Review", tarefas: [] },
  Done: { id: 4, titulo: "Done", tarefas: [] },
});

const colunaParaAdicionar = ref("");

function abrirModalTarefa(nomeColuna) {
  colunaParaAdicionar.value = nomeColuna;
  isModalTarefaVisible.value = true;
}

function adicionarTarefaAoGrupo(dadosDaTarefa) {
  const novaTarefa = {
    ...dadosDaTarefa,
    id: Date.now(),
  };

  if (colunaParaAdicionar.value && colunas[colunaParaAdicionar.value]) {
    colunas[colunaParaAdicionar.value].tarefas.push(novaTarefa);
  }

  isModalTarefaVisible.value = false;
}

function carregarTarefasDoProjeto() {
  const todasAsTarefasDoBackend = [
    { id: 1, titulo: "Tarefa A", estado: "To-do" },
    { id: 2, titulo: "Tarefa B", estado: "Doing" },
    { id: 3, titulo: "Tarefa C", estado: "To-do" },
    { id: 4, titulo: "Tarefa D", estado: "To-do" },
    { id: 5, titulo: "Tarefa C", estado: "To-do" },
    { id: 6, titulo: "Tarefa J", estado: "To-do" },
  ];

  for (const tarefa of todasAsTarefasDoBackend) {
    if (colunas[tarefa.estado]) {
      colunas[tarefa.estado].tarefas.push(tarefa);
    }
  }
}

onMounted(() => {
  carregarTarefasDoProjeto();
});
</script>
