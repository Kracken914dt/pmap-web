export default function DashboardPage() {
  return (
    <section className="space-y-6">
      <div className="rounded-3xl border border-white/10 bg-gradient-to-br from-midnight-700 to-slate-950 p-8 shadow-glow">
        <p className="text-sm uppercase tracking-[0.35em] text-midnight-100">PMAP Modules</p>
        <h2 className="mt-3 text-3xl font-semibold text-white">Control center for users, subjects and study sessions</h2>
        <p className="mt-3 max-w-2xl text-slate-300">
          Manage authentication, subjects and study sessions from a focused dashboard connected to the Spring Boot API.
        </p>
      </div>
      <div className="grid gap-4 md:grid-cols-3">
        {[
          ['Usuarios', 'Login, registro, CRUD y estados.'],
          ['Materias', 'Busqueda, filtro y control de nombres duplicados.'],
          ['Sesiones', 'Seguimiento por usuario, materia y estado.']
        ].map(([title, description]) => (
          <article key={title} className="rounded-3xl border border-white/10 bg-white/5 p-5">
            <h3 className="text-lg font-semibold text-white">{title}</h3>
            <p className="mt-2 text-sm text-slate-300">{description}</p>
          </article>
        ))}
      </div>
    </section>
  );
}