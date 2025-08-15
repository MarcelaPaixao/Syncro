<template>
  <div>
    <AppHeader />
    <SubBar
      @abrirModalMembros="isModalMembrosVisible = true"
      @abrirModalDescricao="isModalDescricaoVisible = true"
    />

    <div class="p-8">
      <h1 class="text-2xl font-bold text-center">{{ nomeDoGrupo }}</h1>
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
      :grupoId="route.params.id"
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
import { useRoute } from "vue-router";
import { getGrupo } from "@/services/grupoService";
import { getTarefasGrupo } from "@/services/tarefaService";
const isModalTarefaVisible = ref(false);
const isModalDescricaoVisible = ref(false);
const isModalMembrosVisible = ref(false);
const route = useRoute();

let listaMembrosGrupo = null;

let descricaoAtual = "  ";
const nomeDoGrupo = ref("Carregando nome do grupo...");
let grupoInfo = null;
const colunas = reactive({
  TODO: { id: 1, titulo: "To-do", tarefas: [] },
  DOING: { id: 2, titulo: "Doing", tarefas: [] },
  REVIEW: { id: 3, titulo: "Review", tarefas: [] },
  DONE: { id: 4, titulo: "Done", tarefas: [] },
});

const colunaParaAdicionar = ref("");

function abrirModalTarefa(nomeColuna) {
  colunaParaAdicionar.value = nomeColuna;
  isModalTarefaVisible.value = true;
}

async function carregarTarefasDoProjeto() {
  const todasAsTarefas = await getTarefasGrupo(route.params.id);

  for (const tarefa of todasAsTarefas) {
    if (colunas[tarefa.status]) {
      colunas[tarefa.status].tarefas.push(tarefa);
    }
  }
}

async function getGrupoInfo() {
  grupoInfo = await getGrupo(route.params.id);
  listaMembrosGrupo = grupoInfo.membros;
  nomeDoGrupo.value = grupoInfo.nome;
}

onMounted(() => {
  carregarTarefasDoProjeto();
  getGrupoInfo();
});
</script>
