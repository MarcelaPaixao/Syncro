import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function createTarefa(tarefaDTO) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const responseTarefa = api.post(`/api/tarefa/create`, tarefaDTO, config);
    console.log((await responseTarefa).data);
    return (await responseTarefa).data;
  } catch (error) {
    return error;
  }
}

export async function getTarefaById(tarefaId) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const tarefa = await api.get(`/api/tarefa/get/${tarefaId}`, config);
    return tarefa.data;
  } catch (error) {
    return error;
  }
}

export async function editaTarefa(tarefaDTO) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const responseTarefa = api.put(`/api/tarefa/edita`, tarefaDTO, config);
    console.log((await responseTarefa).data);
    return (await responseTarefa).data;
  } catch (error) {
    return error;
  }
}
