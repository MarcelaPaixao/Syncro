import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function createTarefa(tarefaDTO) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  console.log(token);
  try {
    const responseTarefa = api.post(`/api/tarefa/create`, tarefaDTO, config);
    console.log((await responseTarefa).data);
    return (await responseTarefa).data;
  } catch (error) {
    return error;
  }
}
