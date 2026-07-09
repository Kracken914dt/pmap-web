import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import http from '../api/http';
import { setAuthSession } from '../utils/storage';

export default function LoginPage() {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { isSubmitting } } = useForm({
    defaultValues: { correo: 'admin@pmap.com', contraseña: 'Password123' }
  });

  async function onSubmit(values) {
    try {
      const { data } = await http.post('/auth/login', values);
      setAuthSession(data.token, data.usuario);
      navigate('/');
    } catch (error) {
      Swal.fire('Login failed', 'Check backend credentials or start the API first.', 'error');
    }
  }

  return (
    <div className="flex min-h-screen items-center justify-center px-4">
      <form onSubmit={handleSubmit(onSubmit)} className="w-full max-w-md rounded-3xl border border-white/10 bg-slate-950/80 p-8 shadow-glow backdrop-blur-xl">
        <p className="text-sm uppercase tracking-[0.35em] text-midnight-200">PMAP</p>
        <h1 className="mt-2 text-3xl font-semibold text-white">Sign in</h1>
        <div className="mt-6 space-y-4">
          <input
            className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3 text-white outline-none placeholder:text-slate-500"
            placeholder="Correo"
            type="email"
            {...register('correo', { required: true })}
          />
          <input
            className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3 text-white outline-none placeholder:text-slate-500"
            placeholder="Contraseña"
            type="password"
            {...register('contraseña', { required: true })}
          />
        </div>
        <button
          type="submit"
          disabled={isSubmitting}
          className="mt-6 w-full rounded-2xl bg-midnight-500 px-4 py-3 font-semibold text-white transition hover:bg-midnight-400 disabled:opacity-60"
        >
          {isSubmitting ? 'Signing in...' : 'Login'}
        </button>
      </form>
    </div>
  );
}