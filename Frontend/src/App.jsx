// Componente raíz de la aplicación
// Delega el enrutamiento completo al componente AppRouter
import { AppRouter } from './routes/AppRouter';

export default function App() {
  return <AppRouter />;
}