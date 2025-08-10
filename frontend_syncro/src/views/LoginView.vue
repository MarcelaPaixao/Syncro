<template>
  <div class="bg-padrao">
    <div class="login-box">
      <img
        alt="Syncro logo"
        src="../assets/syncro_logo_verde.png"
        height="65"
        width="120"
      />
      <h2>Login</h2>

      <form @submit.prevent="fazerLogin">
        <InputEmail v-model:email="email" />

        <InputSenha v-model:password="password" />

        <div class="redireciona-esqueceu-senha">
          <router-link to="/esqueceu-sua-senha"
            >Esqueceu sua senha?</router-link
          >
        </div>

        <BotaoCustomizado texto="Entrar" type="submit" />
      </form>

      <div class="register-redirect">
        <h4>Novo no Syncro?</h4>

        <BotaoCustomizado
          texto="Cadastre-se"
          variante="secundaria"
          @click="redirecionaCadastrar"
        />
      </div>
    </div>
  </div>
</template>

<script>
import InputSenha from "@/components/InputSenha.vue";
import InputEmail from "@/components/InputEmail.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import axios from "axios";
import { setAuthTokens } from "axios-jwt";
export default {
  name: "LoginView",
  components: {
    InputSenha,
    InputEmail,
    BotaoCustomizado,
  },
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async fazerLogin() {
      try {
        const loginDTO = {
          email: this.email,
          senha: this.password,
        };
        const response = await axios.post(
          `http://localhost:8080/api/aluno/login`,
          loginDTO
        );
        setAuthTokens({
          accessToken: response.data.access_token,
          refreshToken: response.data.refresh_token,
        });
        console.log(response.headers);
      } catch (error) {
        console.error("Error posting data:", error);
      }
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
.login-box {
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  max-height: 470px;
}

h4 {
  text-align: center;
  margin-bottom: 1rem;
  color: #333;
  font-size: 0.94rem;
  margin-bottom: 10px;
}

.redireciona-esqueceu-senha {
  text-align: left;
  margin-top: -0.3rem;
  margin-bottom: 1rem;
}

.redireciona-esqueceu-senha a {
  color: #077a7d;
  padding: 0;
  font-size: 0.95em;
  text-decoration: none;
}

.redireciona-esqueceu-senha a:hover {
  color: #0ca4aad4;
  text-decoration: underline;
}

img {
  margin-top: -0.7rem;
  margin-bottom: -0.2rem;
}
</style>
