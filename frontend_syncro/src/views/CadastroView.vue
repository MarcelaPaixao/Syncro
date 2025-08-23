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
        axios.post(`http://localhost:8080/api/aluno/create`, cadastroData);
        this.$router.push("/login");
      } catch (error) {
        console.log("Erro ao cadastrar", error);
      }
    },
  },
};
</script>

<style scoped>
/* O "scoped" faz com que este CSS só se aplique a este componente */
.cadastro-box {
  justify-content: center;
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  max-height: 470px;
}

.login-redirect {
  text-align: center;
  margin-top: 1.2rem;
  /* margin-bottom: -0.6rem; */
}

.login-redirect a {
  color: #077a7d;
  padding: 0;
  font-size: 0.97em;
  text-decoration: none;
}

.login-redirect a:hover {
  color: #0ca4aad4;
  text-decoration: underline;
}

.error-message {
  text-align: left;
  font-size: 0.9rem;
  font-style: italic;
  color: red;
}

img {
  margin-top: -0.7rem;
  margin-bottom: -0.2rem;
}
</style>
