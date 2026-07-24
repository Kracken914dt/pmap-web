// Utilidades para gestionar la sesión del usuario en localStorage
// Almacena y recupera el token JWT y los datos del usuario autenticado

export function getAuthUser() {
  const rawUser = localStorage.getItem('pmap_user');
  return rawUser ? JSON.parse(rawUser) : null;
}

export function getAuthToken() {
  return localStorage.getItem('pmap_token');
}

export function setAuthSession(token, user) {
  localStorage.setItem('pmap_token', token);
  localStorage.setItem('pmap_user', JSON.stringify(user));
}

export function clearAuthSession() {
  localStorage.removeItem('pmap_token');
  localStorage.removeItem('pmap_user');
}