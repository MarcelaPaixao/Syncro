<template>
    <div class="barra-selecao">
        <select v-model="responsavel" required>
            <option value="">Selecione um membro</option>
            <option
              v-for="membro in membrosDoGrupo"
              :key="membro.id"
              :value="membro.id"
            >
              {{ membro.nome }}
            </option>
          </select>
    </div>
</template>

<script setup>
</script>