import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function getAlunosGrupo(GrupoId) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  console.log(token);
  try {
    const responseGrupo = api.get(`/api/aluno/get/${GrupoId}`, config);
    console.log((await responseGrupo).data);
    return (await responseGrupo).data;
  } catch (error) {
    return error;
  }
}
