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

      <div class="mt-auto">
        <BotaoCustomizado type="submit" texto="Salvar" />
      </div>
    </div>

    <div
      class="checklist-container border-l border-gray-300 pl-10 flex flex-col"
    >
      <label class="text-lg font-bold text-gray-700 mb-4">Membros</label>
    </div>
  </form>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
import InputString from "@/components/InputString.vue";
import TextArea from "@/components/TextArea.vue";

export default {
  name: "CriarNovoGrupoView",
  components: {
    BotaoCustomizado,
    AppHeader,
    InputString,
    TextArea,
  },
  data() {
    return {
      name: "",
      materia: "",
      professor: "",
      prazo: "",
      descricao: "",
      membros: [],
      // membrosError: "",
    };
  },
  methods: {
    criarGrupo() {
      // this.membrosError="";

      // if(this.membros.length < 2) {
      //   this.membrosError="Não há membros o suficiente!";
      //   return;
      // }

      console.log("Dados do grupo:", {
        name: this.name,
        materia: this.materia,
        professor: this.professor,
        prazo: this.prazo,
        descricao: this.descricao,
      });
    },
  },
};
</script>
