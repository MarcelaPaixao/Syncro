<template>
  <AppHeader />
  <div class="novo-grupo">
    <BotaoCustomizado type="click" texto="Salvar" />
  </div>
</template>

<script>
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import AppHeader from "@/components/AppHeader.vue";
export default {
  name: "PerfilUsuarioView",
  components: {
    BotaoCustomizado,
    AppHeader,
  },
  data() {
    return {};
  },
  methods: {
    m1() {},
  },
};
</script>

<style scoped></style>
