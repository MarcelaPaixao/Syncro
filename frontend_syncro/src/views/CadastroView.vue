<template>
  <div class="cadastro">
    <div class="cadastro-box">
      <h2>Cadastro Syncro</h2>

      <form @submit.prevent="fazerCadastro">
        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required />
        </div>

        <div class="input-group">
          <label>Senha</label>
          <input type="password" v-model="password" required />
        </div>

        <button>Cadastrar</button>
      </form>

      <div class="register-redirect">
        <h3>Novo no Syncro?</h3>

        <button @click="redirecionaLogin">Entre em sua conta</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CadastroView",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    fazerCadastro() {
      // Testando no console do navegador
      console.log("Dados para cadastro:", {
        email: this.email,
        password: this.password,
      });
    },
    redirecionaLogin() {
      console.log("redirecionando...");
      this.$router.push("/login");
    },
  },
};
</script>

<style></style>
