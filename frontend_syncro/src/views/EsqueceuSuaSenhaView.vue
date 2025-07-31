<template>
  <div class="esqueceu-sua-senha">
    <h2>Redefinir senha</h2>
    <p>Digite o email associado à sua conta</p>
    <div class="validacao-box"></div>
  </div>
</template>

<script>
export default {
  name: "EsqueceuSuaSenhaView",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    m1() {
      // Testando no console do navegador
      console.log(".....");
    },
    m2() {
      console.log("......");
    },
  },
};
</script>

<style></style>
