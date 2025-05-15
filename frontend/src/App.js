import React, { useEffect, useState } from 'react';

function App() {
  const [movies, setMovies] = useState([]);
  const [title, setTitle] = useState('');

  const API_BASE = 'http://localhost:8090/api/movies';

  // Filme laden beim Start
  useEffect(() => {
    fetch(API_BASE)
      .then(res => res.json())
      .then(setMovies)
      .catch(err => console.error('Fehler beim Laden:', err));
  }, []);

  // Neuen Film hinzufügen
  const addMovie = async () => {
    if (!title.trim()) return;

    const res = await fetch(API_BASE, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title })
    });

    const newMovie = await res.json();
    setMovies([...movies, newMovie]);
    setTitle('');
  };

  // Film löschen
  const deleteMovie = async (id) => {
    await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
    setMovies(movies.filter(movie => movie.id !== id));
  };

  return (
    <div className="container mt-5">
      <h1>🎬 Meine Filmliste</h1>

      <div className="input-group mb-3">
        <input
          type="text"
          className="form-control"
          placeholder="Filmtitel eingeben Bitte..."
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <button className="btn btn-primary" onClick={addMovie}>Hinzufügen</button>
      </div>

      <ul className="list-group">
        {movies.map(movie => (
          <li key={movie.id} className="list-group-item d-flex justify-content-between align-items-center">
            {movie.title}
            <button className="btn btn-sm btn-danger" onClick={() => deleteMovie(movie.id)}>🗑️</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
