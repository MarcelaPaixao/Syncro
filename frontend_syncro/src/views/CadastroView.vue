<template>
  <div class="bg-padrao">
    <div class="cadastro-box">
      <img
        alt="Syncro logo"
        src="../assets/syncro_logo_verde.png"
        height="65"
        width="120"
      />
      <h2>Crie sua conta</h2>

      <form @submit.prevent="fazerCadastro">
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

      <div class="login-redirect">
        <router-link to="/login">Já tem uma conta?</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import InputSenha from "@/components/InputSenha.vue";
import InputString from "@/components/InputString.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
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
    fazerCadastro() {
      if (this.password != this.confirmPassword) {
        this.passwordError = "As senhas são diferentes!";
        return;
      }
      // MARCELA:Testando no console do navegador
      console.log("Dados para cadastro:", {
        name: this.name,
        email: this.email,
        password: this.password,
        confirmPassword: this.confirmPassword,
      });
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
