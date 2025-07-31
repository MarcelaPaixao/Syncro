<template>
  <div class="login">
    <div class="login-box">
      <h2>Login Syncro</h2>

      <form @submit.prevent="fazerLogin">
        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required />
        </div>

        <div class="password-group">
          <div class="input-group">
            <label>Senha</label>
            <input type="password" v-model="password" required />
          </div>

          <div class="redireciona-esqueceu-senha">
            <router-link to="/esqueceu-sua-senha"
              >Esqueceu sua senha?</router-link
            >
          </div>
        </div>

        <button class="button">Entrar</button>
      </form>

      <div class="register-redirect">
        <h4>Novo no Syncro?</h4>

        <button @click="redirecionaCadastrar" class="button" id="cadastro-btn">
          Cadastre-se
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    fazerLogin() {
      // Testando no console do navegador
      // aqui faz chamada para backend
      console.log("Dados para login:", {
        email: this.email,
        password: this.password,
      });
    },
    redirecionaCadastrar() {
      console.log("redirecionando cadastro...");
      this.$router.push("/cadastro");
    },
  },
};
</script>

<style scoped>
/* O "scoped" faz com que este CSS só se aplique a este componente */
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: linear-gradient(160deg, #a7f7dc, #33af90, #0d684c);
}

.login-box {
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
  margin-bottom: 1rem;
  color: #333;
  font-size: 0.9rem;
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
  transition: background-color 0.2s;
}

.button:hover {
  background-color: #10a075cb;
}

#cadastro-btn {
  background-color: rgb(101, 165, 146);
}

#cadastro-btn:hover {
  background-color: rgba(124, 202, 174, 0.938);
}

.redireciona-esqueceu-senha {
  text-align: left;
  margin-top: -0.5rem;
  margin-bottom: 1.5rem;
}

.redireciona-esqueceu-senha a {
  color: #0d8668;
  padding: 0;
  font-size: 0.92em;
  text-decoration: none;
}

.redireciona-esqueceu-senha a:hover {
  color: #0d8668a2;
  text-decoration: underline;
}
</style>
