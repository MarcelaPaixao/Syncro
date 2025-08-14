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
    <div class="grid grid-cols-4 gap-8 px-4">
      <div class="flex flex-col items-center">
        <label class="text-xl font-bold text-gray-700 mb-2">To-do</label>
        <div class="bg-gray-100 p-4 rounded-2xl shadow-xl w-full min-h-[60vh]">
          <BotaoCustomizado
            @click="isModalTarefaVisible = true"
            texto="Nova tarefa"
          />
          <CriarTarefaModal
            :visivel="isModalTarefaVisible"
            :membrosDoGrupo="listaMembrosGrupo"
            @close="isModalTarefaVisible = false"
            @salvar="adicionarTarefaAoGrupo"
          />
        </div>
      </div>

      <div class="flex flex-col items-center">
        <label class="text-xl font-bold text-gray-700 mb-2">Doing</label>
        <div
          class="bg-gray-100 p-4 rounded-2xl shadow-xl w-full min-h-[60vh]"
        ></div>
      </div>

      <div class="flex flex-col items-center">
        <label class="text-xl font-bold text-gray-700 mb-2">Review</label>
        <div
          class="bg-gray-100 p-4 rounded-2xl shadow-xl w-full min-h-[60vh]"
        ></div>
      </div>

      <div class="flex flex-col items-center">
        <label class="text-xl font-bold text-gray-700 mb-2">Done</label>
        <div
          class="bg-gray-100 p-4 rounded-2xl shadow-xl w-full min-h-[60vh]"
        ></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import CriarTarefaModal from "@/components/CriarTarefaModal.vue";
import VerDescricaoModal from "@/components/VerDescricaoModal.vue";
import VerMembrosModal from "@/components/VerMembrosModal.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import SubBar from "@/components/SubBar.vue";

const isModalTarefaVisible = ref(false);
const isModalDescricaoVisible = ref(false);
const isModalMembrosVisible = ref(false);

const listaMembrosGrupo = ref([
  { id: 1, nome: "Marcela", email: "marcela.paixao@edu.ufes.br" },
  { id: 2, nome: "Malu", email: "malu@a.com" },
  { id: 3, nome: "Fulano", email: "fulaninho@a.com" },
  { id: 4, nome: "Caramelo", email: "melinho@a.com" },
  { id: 5, nome: "Belinha", email: "belinha@a.com" },
]);

const descricaoAtual = ref("exemploexemploexemplo");

function adicionarTarefaAoGrupo(dadosDaTarefa) {
  console.log("Tarefa recebida:", dadosDaTarefa);
  isModalTarefaVisible.value = false;
}
</script>
