// Hook personalizado para obtener los datos del usuario autenticado
// Lee la información desde localStorage a través de la función getAuthUser
import { getAuthUser } from '../utils/storage';

export function useAuth() {
  return {
    user: getAuthUser()
  };
}