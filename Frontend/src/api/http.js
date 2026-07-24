// Configuración de Axios para la comunicación con el backend
// Incluye la URL base y un interceptor que añade el token JWT en cada petición
import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:8080/api'
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