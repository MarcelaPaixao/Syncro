<template>
  <div class="esqueceu-sua-senha">
    <div class="validar-email-box">
      <h2>Redefinir senha</h2>

      <p>Digite o email associado à sua conta</p>
      <form @submit.prevent="validarEmail">
        <InputEmail v-model:email="email" />
        <button type="submit" class="button">Enviar</button>
      </form>
      <p v-if="emailError" class="error-message">
        {{ emailError }}
      </p>
    </div>
  </div>
</template>

<script>
import InputEmail from "@/components/InputEmail.vue";
export default {
  name: "EsqueceuSuaSenhaView",
  components: {
    InputEmail,
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
.esqueceu-sua-senha {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: linear-gradient(160deg, #a7f7dc, #33af90, #0d684c);
}

.validar-email-box {
  /* justify-content: center; */
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
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

.error-message {
  text-align: left;
  font-size: 0.9rem;
  margin-top: -0.5rem;
  font-style: italic;
  color: red;
}

p {
  text-align: left;
}
</style>
