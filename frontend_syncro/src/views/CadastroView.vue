<template>
  <div class="bg-padrao">
    <div
      class="flex flex-col items-center bg-white p-8 rounded-[30px] shadow-xl w-full max-w-sm"
    >
      <img
        alt="Syncro logo"
        src="../assets/syncro_logo_verde.png"
        height="65"
        width="120"
        class="mb-4"
      />
      <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">
        Crie sua conta
      </h2>

      <form
        @submit.prevent="fazerCadastro"
        class="flex flex-col items-center w-full space-y-4"
      >
        <InputString
          v-model="name"
          type="text"
          label="Nome completo"
          placeholder=""
        />

        <InputString
          v-model="email"
          tipo="email"
          label="Email"
          placeholder=""
        />

        <InputSenha
          v-model:password="password"
          v-model:confirmPassword="confirmPassword"
          :passwordError="passwordError"
          :showConfirmacao="true"
        />

        <BotaoCustomizado texto="Cadastrar" type="submit" />
      </form>

      <div class="login-redirect text-center mt-6">
        <router-link to="/login" class="text-sm text-[#077a7d] hover:underline"
          >Já tem uma conta?</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import InputSenha from "@/components/InputSenha.vue";
import InputString from "@/components/InputString.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import axios from "axios";
import emitter from "@/eventBus";

export default {
  name: "CadastroView",
  components: {
    InputSenha,
    InputString,
    BotaoCustomizado,
  },
  data() {
    return {
      email: "",
      password: "",
      confirmPassword: "",
      name: "",
      passwordError: "",
    };
  },
  watch: {
    password() {
      this.passwordError = "";
    },
    confirmPassword() {
      this.passwordError = "";
    },
  },
  methods: {
    async fazerCadastro() {
      try {
        if (this.password != this.confirmPassword) {
          this.passwordError = "As senhas são diferentes!";
          return;
        }
        const cadastroData = {
          nome: this.name,
          email: this.email,
          senha: this.password,
        };
        const response = axios.post(
          `http://localhost:8080/api/aluno/create`,
          cadastroData
        );
        emitter.emit("show-notification", {
          message: "Usuário criado com sucesso!",
          type: "success",
        });
        console.log((await response).data);
      } catch (error) {
        console.log("Erro ao cadastrar", error);
        emitter.emit("show-notification", {
          message: "Erro ao criar usuário. Tente novamente.",
          type: "error",
        });
      }
    },
  },
};
</script>

<style scoped>
img {
  margin-top: -0.7rem;
  margin-bottom: -0.2rem;
}
</style>
