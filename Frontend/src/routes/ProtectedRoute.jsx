// Ruta protegida: redirige al login si el usuario no tiene un token JWT válido
import { Navigate, Outlet } from 'react-router-dom';
import { getAuthToken } from '../utils/storage';

export default function ProtectedRoute() {
  return getAuthToken() ? <Outlet /> : <Navigate to="/login" replace />;
}