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
      <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">Login</h2>

      <form
        @submit.prevent="fazerLogin"
        class="flex flex-col items-center w-full space-y-4"
      >
        <InputString
          v-model="email"
          label="Email"
          type="email"
          placeholder=""
        />

        <InputSenha v-model:password="password" />

        <div class="w-full text-left mt-[-0.5rem] mb-2">
          <router-link
            to="/esqueceu-sua-senha"
            class="text-sm text-[#077a7d] hover:underline"
            >Esqueceu sua senha?</router-link
          >
        </div>

        <BotaoCustomizado texto="Entrar" type="submit" />
      </form>

      <div class="mt-6 w-full text-center">
        <h4 class="text-sm text-gray-600 mb-2">Novo no Syncro?</h4>

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
import InputString from "@/components/InputString.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
import { getAccessToken, setAuthTokens } from "axios-jwt";
import axios from "axios";
export default {
  name: "LoginView",
  components: {
    InputSenha,
    BotaoCustomizado,
    InputString,
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
          accessToken: response.data.token,
        });
        console.log(getAccessToken());
        this.$router.push("/perfil-usuario");
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
