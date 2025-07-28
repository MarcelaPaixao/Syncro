<template>
  <div class="cadastro">
    <div class="cadastro-box">
      <h2>Cadastro Syncro</h2>

      <form @submit.prevent="fazerCadastro">
        <div class="input-group">
          <label>Digite seu nome</label>
          <input type="name" v-model="name" required />
        </div>

        <div class="input-group">
          <label>Digite um email</label>
          <input type="email" v-model="email" required />
        </div>

        <div class="input-group">
          <label>Digite sua senha</label>
          <input type="password" v-model="password" required />
        </div>
        <!-- fazer lógica de verificação de senhas que devem ser iguais -->
        <div class="input-group">
          <label>Digite sua senha novamente</label>
          <input type="password" v-model="password" required />
        </div>

        <button>Cadastrar</button>
      </form>

      <div class="login-redirect">
        <router-link to="/login">Já tem uma conta?</router-link>
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
      name: "",
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
  },
};
</script>

<style scoped>
/* O "scoped" faz com que este CSS só se aplique a este componente */
.cadastro {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
  background-color: #f4f4f8;
}

.cadastro-box {
  /* justify-content: center; */
  padding: 2rem;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

h4 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
  font-size: 1rem;
}

.input-group {
  margin-bottom: 1rem;
}

label {
  text-align: left;
  display: block;
  margin-bottom: 0.5rem;
  color: #555;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-sizing: border-box; /* Garante que o padding não aumente a largura total */
}

button {
  width: 100%;
  padding: 0.6rem;
  border: none;
  background-color: #0d8668;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0d8668a2;
}

.login-redirect {
  text-align: center;
  margin-top: 1.5rem;
  margin-bottom: 1rem;
}

.login-redirect a {
  color: #0d8668;
  padding: 0;
  font-size: 0.97em;
  text-decoration: underline;
}

.login-redirect a:hover {
  color: #0d8668a2;
  text-decoration: none;
}
</style>
