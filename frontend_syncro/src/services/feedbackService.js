import api from "./api";
import { getAccessToken } from "axios-jwt";

export async function createFeedback(feedbackDTO) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const responseFeedback = api.post(
      `/api/feedback/create`,
      feedbackDTO,
      config
    );
    console.log((await responseFeedback).data);
    return (await responseFeedback).data;
  } catch (error) {
    return error;
  }
}
export async function getFeedbacksTarefa(tarefaId) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const response = api.get(`/api/feedback/get/${tarefaId}`, config);
    console.log((await response).data);
    return (await response).data;
  } catch (error) {
    return error;
  }
}
export async function editaFeedback(feedbackDTO) {
  const token = await getAccessToken();
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  try {
    const responseFeedback = api.put(
      `/api/feedback/edita`,
      feedbackDTO,
      config
    );

    return (await responseFeedback).status;
  } catch (error) {
    return error;
  }
}
