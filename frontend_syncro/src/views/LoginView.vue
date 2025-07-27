<template>
  <div class="login">
    <div class="login-box">
      <h2>Login Syncro</h2>

      <form @submit.prevent="fazerLogin">
        <div class="input-group">
          <label>Email</label>
          <input type="email" v-model="email" required />
        </div>

        <div class="input-group">
          <label>Senha</label>
          <input type="password" v-model="password" required />
        </div>

        <button>Entrar</button>
      </form>

      <div class="redireciona-esqueceu-senha">
        <button @click="redirecionaNovaSenha" id="esqueceuSenha">Esqueceu sua senha?</button>
      </div>
      <div class="register-redirect">
        <h4>Novo no Syncro?</h4>

        <button @click="redirecionaCadastrar">Cadastre-se</button>
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
      console.log("Dados para login:", {
        email: this.email,
        password: this.password,
      });
    },
    redirecionaCadastrar() {
      console.log("redirecionando cadastro...");
      this.$router.push("/cadastro");
    },
    redirecionaNovaSenha() {
      console.log("redirecionando esqueceu senha...");
      this.$router.push("/esqueceu-sua-senha");
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
  height: 80vh;
  background-color: #f4f4f8;
}

.login-box {
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
  background-color: #357abd;
}

#esqueceuSenha {
  background-color: none;
}

</style>
