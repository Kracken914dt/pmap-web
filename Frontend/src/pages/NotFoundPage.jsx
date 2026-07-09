import { Link } from 'react-router-dom';

export default function NotFoundPage() {
  return (
    <div className="flex min-h-screen items-center justify-center px-4 text-center text-white">
      <div className="max-w-md rounded-3xl border border-white/10 bg-white/5 p-8">
        <p className="text-sm uppercase tracking-[0.35em] text-midnight-200">404</p>
        <h2 className="mt-2 text-3xl font-semibold">Page not found</h2>
        <Link className="mt-6 inline-flex rounded-full bg-midnight-500 px-5 py-2 font-medium" to="/">
          Go home
        </Link>
      </div>
    </div>
  );
}