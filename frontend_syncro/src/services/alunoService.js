import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function getAlunosGrupo(GrupoId) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  if (GrupoId == null || GrupoId == undefined) return;
  try {
    const response = api.get(`/api/aluno/get/${GrupoId}`, config);
    console.log((await response).data);
    return (await response).data;
  } catch (error) {
    return error;
  }
}

export async function getUser() {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  try {
    console.log("entrou aqui");
    const response = api.get(`/api/aluno/get/UserId`, config);
    return (await response).data;
  } catch (error) {
    return error;
  }
}
