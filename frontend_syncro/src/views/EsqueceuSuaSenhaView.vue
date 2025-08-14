<template>
  <div class="bg-padrao">
    <div
      class="bg-white p-8 rounded-[30px] shadow-xl w-full max-w-sm min-h-[290px] flex flex-col gap-4"
    >
      <div class="text-center">
        <h2 class="text-2xl font-bold text-gray-800">Redefinir senha</h2>
        <h4 class="text-base text-gray-600 mt-2">
          Digite o email associado à sua conta
        </h4>
      </div>

      <form
        @submit.prevent="validarEmail"
        class="flex flex-col items-center space-y-4 mt-auto h-full justify-between"
      >
        <InputString
          v-model="email"
          type="email"
          label="Email"
          placeholder=""
        />
        <BotaoCustomizado texto="Enviar" type="submit" />
      </form>

      <p v-if="emailError" class="text-sm text-red-600 italic">
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
