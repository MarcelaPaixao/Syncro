<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2 class="text-2xl font-bold text-gray-800">Descrição</h2>
    </template>

    <form
      id="form-descricao"
      @submit.prevent="salvarDescricao"
      class="space-y-4"
    >
      <TextArea v-model="descricaoLocal" label="Descrição" />
    </form>

    <template v-slot:footer>
      <BotaoCustomizado type="submit" form="form-descricao" texto="Salvar" />
    </template>
  </BaseModal>
</template>

<script>
import BotaoCustomizado from "./BotaoCustomizado.vue";
import TextArea from "./TextArea.vue";
import BaseModal from "./BaseModal.vue";

export default {
  name: "VerDescricaoModal",
  components: {
    BotaoCustomizado,
    TextArea,
    BaseModal,
  },

  props: {
    visivel: { type: Boolean, required: true },
    descricao: { type: String, default: "" },
  },
  emits: ["close", "salvar"],

  data() {
    return {
      descricaoLocal: "",
    };
  },

  watch: {
    visivel(newVal) {
      if (newVal) {
        this.descricaoLocal = this.descricao;
      }
    },
  },

  methods: {
    salvarDescricao() {
      this.$emit("salvar", this.descricaoLocal);
      this.$emit("close");
    },
  },
};
</script>
