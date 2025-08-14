import axios from "axios";
import { applyAuthTokenInterceptor, getAccessToken } from "axios-jwt";

const api = axios.create({
  baseURL: "http://localhost:8080",
});
applyAuthTokenInterceptor(api, {
  request: async (request) => {
    const token = await getAccessToken();
    console.log(token);
    if (token) {
      request.headers.Authorization = `Bearer ${token}`;
    }
    return request;
  },
});

export default api;
