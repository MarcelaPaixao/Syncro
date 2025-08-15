import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function getGruposAluno() {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  console.log(token);
  try {
    const response = api.get(`/api/grupo/get`, config);
    console.log((await response).data);
    return (await response).data;
  } catch (error) {
    return error;
  }
}

export async function getGrupo(grupoId) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  console.log(token);
  try {
    const response = api.get(`/api/grupo/getGrupo/${grupoId}`, config);
    console.log((await response).data);
    return (await response).data;
  } catch (error) {
    return error;
  }
}
