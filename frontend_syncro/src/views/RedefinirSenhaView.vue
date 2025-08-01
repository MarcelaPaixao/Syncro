<template>
  <div class="bg-padrao">
    <div class="redefinir-senha-box">
      <h2>Redefinir senha</h2>
      <form @submit.prevent="redefineSenha">
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

<style scoped>
.redefinir-senha-box {
  justify-content: center;
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  max-height: 250px;
}

/* .button {
  margin-top: 1rem;
  margin-bottom: 0.5rem;
} */
</style>
