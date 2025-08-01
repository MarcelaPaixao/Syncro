<template>
    <div class="novo-grupo">
        <form @submit="criarGrupo">
            <div class="input-group">
                <label>Nome</label>
                
            </div>
            <div class="input-group">
                <label>Matéria</label>
            </div>
            <div class="input-group">
                <label>Professor</label>
            </div>
            <div class="input-group">
                <label>Data</label>
            </div>
            <div class="input-group">
                <label>Descrição</label>
            </div>
            <div class="input-group">
                <label>Membros</label>
            </div>

            <BotaoCustomizado type="submit" texto="Salvar" />
        </form>
    </div>

</template>
  
<script>
import BotaoCustomizado from '@/components/BotaoCustomizado.vue';
export default {
    name: "CriarNovoGrupoView",
    components: {
        BotaoCustomizado,
    },
    data() {
        return {

        };
    },
    methods: {
        m1() {

        },
    },
};
</script>
  
<style scoped>
</style>
  