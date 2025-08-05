<template>
  <div class="bg-padrao">
    <div class="validar-email-box">
      <h2>Redefinir senha</h2>

      <h4>Digite o email associado à sua conta</h4>
      <form @submit.prevent="validarEmail">
        <InputString v-model:email="email" tipo="email" rotulo="Email" />
        <BotaoCustomizado texto="Enviar" type="submit" />
      </form>
      <p v-if="emailError" class="error-message">
        {{ emailError }}
      </p>
    </div>
  </div>
</template>

<script>
import InputString from "@/components/InputString.vue";
import BotaoCustomizado from "@/components/BotaoCustomizado.vue";
export default {
  name: "EsqueceuSuaSenhaView",
  components: {
    InputString,
    BotaoCustomizado,
  },
  watch: {
    email() {
      this.emailError = "";
    },
  },
  data() {
    return {
      email: "",
      emailError: "",
    };
  },

  methods: {
    validarEmail() {
      //verifica se existe no banco de dados um usuario
      //associado a esse email
      // this.emailError = "Não existe uma conta associada à essa conta!";
      // return;
      //else
      console.log("indo para redefinição de senha...");
      this.$router.push("/redefinir-senha");
    },
  },
};
</script>

<style scoped>
.validar-email-box {
  justify-content: center;
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  min-height: 250px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* .button {
  margin-top: 1rem;
  margin-bottom: 0.5rem;
} */

.error-message {
  text-align: left;
  font-size: 0.9rem;
  margin-top: -0.5rem;
  font-style: italic;
  color: red;
}

p,
h4 {
  text-align: left;
}
</style>
