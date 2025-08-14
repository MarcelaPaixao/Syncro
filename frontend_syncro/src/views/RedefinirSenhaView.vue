<template>
  <div class="bg-padrao">
    <div
      class="bg-white p-8 rounded-[30px] shadow-xl w-full max-w-sm max-h-[290px] flex flex-col items-center"
    >
      <h2 class="text-2xl font-bold text-gray-800 text-center">
        Redefinir senha
      </h2>

      <form
        @submit.prevent="redefineSenha"
        class="flex flex-col h-full justify-between w-full items-center"
      >
        <InputSenha
          v-model:password="password"
          v-model:confirmPassword="confirmPassword"
          :passwordError="passwordError"
          :showConfirmacao="true"
        />

        <BotaoCustomizado texto="Redefinir" type="submit" />
      </form>
    </div>
  </div>
</template>

<script>
import InputSenha from "@/components/InputSenha.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
export default {
  name: "RedefinirSenhaView",
  components: {
    InputSenha,
    BotaoCustomizado,
  },
  watch: {
    password() {
      this.passwordError = "";
    },
    confirmPassword() {
      this.passwordError = "";
    },
  },
  data() {
    return {
      password: "",
      confirmPassword: "",
      passwordError: "",
    };
  },

  methods: {
    redefineSenha() {
      if (this.password != this.confirmPassword) {
        this.passwordError = "As senhas são diferentes!";
        return;
      }
      // Testando no console do navegador
      //aqui conecta com o backend
      console.log("Dados para redefinicao:", {
        password: this.password,
        confirmPassword: this.confirmPassword,
      });
    },
  },
};
</script>
