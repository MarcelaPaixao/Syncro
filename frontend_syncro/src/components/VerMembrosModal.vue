<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2 class="text-2xl font-bold text-gray-800">Membros</h2>
    </template>

    <div class="space-y-4 max-h-[300px] overflow-y-auto pr-2">
      <div
        v-for="membro in membrosDoGrupo"
        :key="membro.id"
        @click="selecionarMembro(membro.id)"
        class="flex items-center gap-4 p-2 rounded-xl hover:bg-gray-100"
      >
        <div
          class="w-10 h-10 rounded-full bg-[#43bfc6] text-white flex items-center justify-center font-bold flex-shrink-0"
        >
          {{ membro.nome.charAt(0).toUpperCase() }}
        </div>

        <span class="font-medium text-gray-700">{{ membro.nome }}</span>

        <div
          v-if="membroSelecionadoId === membro.id"
          class="mt-1 pl-12 text-base text-gray-500"
        >
          {{ membro.email }}
        </div>
      </div>
    </div>

    <template v-slot:footer> </template>
  </BaseModal>
</template>

<script>
import BaseModal from "./BaseModal.vue";

export default {
  name: "CriarTarefaModal",
  components: {
    BaseModal,
  },
  data() {
    return {
      membroSelecionadoId: null,
    };
  },
  methods: {
    selecionarMembro(membroId) {
      if (this.membroSelecionadoId === membroId) {
        this.membroSelecionadoId = null;
      } else {
        this.membroSelecionadoId = membroId;
      }
    },
  },
  props: {
    visivel: { type: Boolean, required: true },
    membrosDoGrupo: { type: Array, required: true },
  },
  emits: ["close", "salvar"],
};
</script>
