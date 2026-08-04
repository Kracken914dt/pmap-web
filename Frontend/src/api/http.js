// Configuración de Axios para la comunicación con el backend
// Incluye la URL base y un interceptor que añade el token JWT en cada petición
import axios from 'axios';

// API base URL is configurable:
// - Dev: set VITE_API_URL in Frontend/.env (see .env.example)
// - Docker image: injected at build time via the VITE_API_URL build arg
// - Fallback: local development against the backend on localhost
const baseURL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const http = axios.create({
  baseURL
});

// Interceptor de solicitudes: agrega el token de autenticación al encabezado Authorization
http.interceptors.request.use((config) => {
  const token = localStorage.getItem('pmap_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default http;