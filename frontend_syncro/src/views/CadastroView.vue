<template>
  <div class="cadastro">
    <div class="cadastro-box">
      <h2>Cadastro Syncro</h2>

      <form @submit.prevent="fazerCadastro">
        <div class="input-group">
          <label>Nome completo</label>
          <input type="text" v-model="name" required />
        </div>

        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required />
        </div>

        <Senha
          v-model:password="password"
          v-model:confirmPassword="confirmPassword"
          :passwordError="passwordError"
        />

        <button type="submit" class="button">Cadastrar</button>
      </form>

      <div class="login-redirect">
        <router-link to="/login">Já tem uma conta?</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import Senha from "@/components/Senha.vue";
export default {
  name: "CadastroView",
  components: {
    Senha,
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
      // Testando no console do navegador
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
.cadastro {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: linear-gradient(160deg, #a7f7dc, #33af90, #0d684c);
}

.cadastro-box {
  /* justify-content: center; */
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
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
  border-radius: 10px;
  box-sizing: border-box; /* Garante que o padding não aumente a largura total */
}

.button {
  width: 100%;
  padding: 0.6rem;
  border: none;
  background-color: #0d8668;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 1rem;
}

.button:hover {
  background-color: #10a075cb;
}

.login-redirect {
  text-align: center;
  margin-top: 1.5rem;
}

.login-redirect a {
  color: #0d8668;
  padding: 0;
  font-size: 0.97em;
  text-decoration: none;
}

.login-redirect a:hover {
  color: #0d8668a2;
  text-decoration: underline;
}

.error-message {
  text-align: left;
  font-size: 0.9rem;
  margin-top: -0.5rem;
  font-style: italic;
  color: red;
}
</style>
