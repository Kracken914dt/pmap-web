import { getAuthUser } from '../utils/storage';

export default function PerfilPage() {
  const user = getAuthUser();

  return (
    <div className="rounded-3xl border border-white/10 bg-white/5 p-6 text-white">
      <h2 className="text-2xl font-semibold">Perfil</h2>
      <pre className="mt-4 overflow-auto rounded-2xl bg-slate-950/60 p-4 text-sm text-slate-200">{JSON.stringify(user, null, 2)}</pre>
    </div>
  );
}