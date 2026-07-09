import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import http from '../api/http';
import { setAuthSession } from '../utils/storage';
import { Sparkles } from 'lucide-react';

function FormErrorTooltip({ error }) {
  if (!error) return null;
  return (
    <div className="relative mt-2 z-10 flex items-start gap-2.5 rounded-xl border border-slate-200 bg-white p-3 text-xs text-slate-800 shadow-xl animate-fade-in">
      {/* Pointer triangle */}
      <div className="absolute -top-1.5 left-6 h-3 w-3 rotate-45 border-l border-t border-slate-200 bg-white" />
      <span className="flex h-5 w-5 shrink-0 items-center justify-center rounded bg-amber-600 text-white font-bold select-none text-[11px]">
        !
      </span>
      <div className="flex-1 text-left leading-relaxed font-medium">
        {error.message || 'Dato inválido'}
      </div>
    </div>
  );
}

export default function LoginPage() {
  const navigate = useNavigate();
  const [isRegistering, setIsRegistering] = useState(false);

  const { register, handleSubmit, reset, formState: { errors, isSubmitting } } = useForm({
    defaultValues: { correo: '', contraseña: '' }
  });

  async function onSubmit(values) {
    try {
      if (isRegistering) {
        // Register flow
        const { data } = await http.post('/auth/register', {
          ...values,
          rol: values.rol || 'ESTUDIANTE'
        });
        setAuthSession(data.token, data.usuario);
        Swal.fire('¡Registro exitoso!', 'Tu cuenta ha sido creada y has iniciado sesión.', 'success');
        navigate('/');
      } else {
        // Login flow
        const { data } = await http.post('/auth/login', {
          correo: values.correo,
          contraseña: values.contraseña
        });
        setAuthSession(data.token, data.usuario);
        navigate('/');
      }
    } catch (error) {
      console.error('Error en autenticación', error);
      const title = isRegistering ? 'Error de registro' : 'Error de autenticación';
      const msg = error.response?.data?.message || (isRegistering 
        ? 'No se pudo completar el registro. Verifica los datos o si el correo ya existe.'
        : 'Credenciales incorrectas o el servidor backend no responde.');
      Swal.fire(title, msg, 'error');
    }
  }

  function handleToggleMode() {
    setIsRegistering(!isRegistering);
    reset({
      nombres: '',
      apellidos: '',
      correo: '',
      contraseña: '',
      rol: 'ESTUDIANTE'
    });
  }

  return (
    <div className="flex min-h-screen items-center justify-center px-4 bg-slate-950/20 py-12">
      <div className="w-full max-w-md rounded-3xl border border-white/10 bg-slate-950/70 p-8 shadow-2xl backdrop-blur-2xl transition-all duration-300 hover:border-white/20">
        <div className="text-center">
          <div className="inline-flex items-center gap-2 rounded-full bg-midnight-500/10 px-4 py-1.5 text-xs font-semibold uppercase tracking-[0.3em] text-midnight-300">
            <Sparkles className="h-3 w-3 animate-pulse" /> PMAP
          </div>
          <h1 className="mt-4 text-3xl font-bold tracking-tight text-white">
            {isRegistering ? 'Crear Cuenta' : 'Iniciar Sesión'}
          </h1>
          <p className="mt-2 text-sm text-slate-400">
            {isRegistering ? 'Únete a la plataforma de aprendizaje PMAP' : 'Accede al Panel de Módulos del Software'}
          </p>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="mt-8 space-y-4">
          {isRegistering && (
            <div className="grid gap-4 sm:grid-cols-2">
              <div>
                <label className="block text-xs font-medium uppercase tracking-wider text-slate-400 mb-1.5">
                  Nombres
                </label>
                <input
                  className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3 text-white outline-none focus:border-midnight-500 focus:bg-white/10 transition text-sm"
                  placeholder="Juan"
                  type="text"
                  {...register('nombres', { 
                    required: 'Incluye tus nombres. Este campo es obligatorio.' 
                  })}
                />
                <FormErrorTooltip error={errors.nombres} />
              </div>
              <div>
                <label className="block text-xs font-medium uppercase tracking-wider text-slate-400 mb-1.5">
                  Apellidos
                </label>
                <input
                  className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3 text-white outline-none focus:border-midnight-500 focus:bg-white/10 transition text-sm"
                  placeholder="Pérez"
                  type="text"
                  {...register('apellidos', { 
                    required: 'Incluye tus apellidos. Este campo es obligatorio.' 
                  })}
                />
                <FormErrorTooltip error={errors.apellidos} />
              </div>
            </div>
          )}

          <div>
            <label className="block text-xs font-medium uppercase tracking-wider text-slate-400 mb-1.5">
              Correo Electrónico
            </label>
            <input
              className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3.5 text-white outline-none placeholder:text-slate-600 focus:border-midnight-500 focus:bg-white/10 transition text-sm"
              placeholder="correo@ejemplo.com"
              type="text"
              {...register('correo', { 
                required: 'Introduce tu dirección de correo electrónico.',
                pattern: {
                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                  message: 'Incluye un signo "@" en la dirección de correo electrónico. La dirección no es válida.'
                }
              })}
            />
            <FormErrorTooltip error={errors.correo} />
          </div>

          <div>
            <label className="block text-xs font-medium uppercase tracking-wider text-slate-400 mb-1.5">
              Contraseña
            </label>
            <input
              className="w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-3.5 text-white outline-none placeholder:text-slate-600 focus:border-midnight-500 focus:bg-white/10 transition text-sm"
              placeholder="••••••••"
              type="password"
              {...register('contraseña', { 
                required: 'Introduce una contraseña para continuar.',
                minLength: {
                  value: 6,
                  message: 'La contraseña es demasiado corta. Debe tener al menos 6 caracteres.'
                }
              })}
            />
            <FormErrorTooltip error={errors.contraseña} />
          </div>

          {isRegistering && (
            <div>
              <label className="block text-xs font-medium uppercase tracking-wider text-slate-400 mb-1.5">
                Rol
              </label>
              <select
                {...register('rol', { required: 'Por favor, selecciona un rol.' })}
                className="w-full rounded-2xl border border-white/10 bg-slate-900 px-4 py-3 text-white outline-none focus:border-midnight-500 transition text-sm"
              >
                <option value="ESTUDIANTE">ESTUDIANTE</option>
                <option value="ADMINISTRADOR">ADMINISTRADOR</option>
              </select>
              <FormErrorTooltip error={errors.rol} />
            </div>
          )}

          <button
            type="submit"
            disabled={isSubmitting}
            className="mt-4 w-full rounded-2xl bg-midnight-600 py-3.5 font-semibold text-white transition-all hover:bg-midnight-500 active:scale-[0.98] disabled:opacity-50 disabled:pointer-events-none shadow-lg shadow-midnight-500/20 text-sm"
          >
            {isSubmitting 
              ? (isRegistering ? 'Creando cuenta...' : 'Iniciando sesión...') 
              : (isRegistering ? 'Registrarse' : 'Ingresar')}
          </button>
        </form>

        <div className="mt-6 text-center">
          <button
            onClick={handleToggleMode}
            className="text-xs font-medium text-midnight-300 hover:text-midnight-200 transition"
          >
            {isRegistering 
              ? '¿Ya tienes una cuenta? Inicia sesión' 
              : '¿No tienes cuenta? Regístrate aquí'}
          </button>
        </div>
      </div>
    </div>
  );
}