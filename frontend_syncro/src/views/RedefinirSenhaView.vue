<template>
  <div class="redefinir-senha">
    <div class="redefinir-senha-box">
      <h2>Redefinir senha</h2>
      <form @submit.prevent="redefineSenha">
        <InputSenha
          v-model:password="password"
          v-model:confirmPassword="confirmPassword"
          :passwordError="passwordError"
          :showConfirmacao="true"
        />

        <button type="submit" class="button">Redefinir</button>
      </form>
    </div>
  </div>
</template>

<script>
import InputSenha from "@/components/InputSenha.vue";
export default {
  name: "RedefinirSenhaView",
  components: {
    InputSenha,
  },
  watch: {
    password() {
      this.passwordError = "";
    },
    confirmPassword() {
      this.passwordError = "";
    },
  },
  data() {
    return {
      password: "",
      confirmPassword: "",
      passwordError: "",
    };
  },

  methods: {
    redefineSenha() {
      if (this.password != this.confirmPassword) {
        this.passwordError = "As senhas são diferentes!";
        return;
      }
      // Testando no console do navegador
      //aqui conecta com o backend
      console.log("Dados para redefinicao:", {
        password: this.password,
        confirmPassword: this.confirmPassword,
      });
    },
  },
};
</script>

<style scoped>
.redefinir-senha {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: linear-gradient(160deg, #a7f7dc, #33af90, #0d684c);
}

.redefinir-senha-box {
  /* justify-content: center; */
  padding: 2rem;
  background-color: white;
  border-radius: 30px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
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
</style>
