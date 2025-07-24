import './styles/ClienteList.css';
import { useEffect, useState } from 'react';

interface Cliente {
  id: number;
  nombre: string;
  identificacion: string;
  direccion: string;
  telefono: string;
  genero: string;
  edad: string;
  nombreUsuario: string;
  contrasena: string;
  estado: string;
}

const ClienteList = () => {
  const [busqueda, setBusqueda] = useState('');
  const [error, setError] = useState('');
  const [clientes, setClientes] = useState<Cliente[]>([]);

 
  const obtenerTodos = async () => {
    try {
      const response = await fetch('http://localhost:8080/clientes');
      if (!response.ok) throw new Error('No se pudo obtener la lista de clientes');
      const data = await response.json();
      setClientes(data);
      setError('');
    } catch (err: any) {
      setClientes([]);
      setError(err.message || 'Error al cargar clientes');
    }
  };


  useEffect(() => {
    obtenerTodos();
  }, []);

  
  const buscarCliente = async () => {
    if (!busqueda) return;
    try {
      const response = await fetch(`http://localhost:8080/clientes/${busqueda}`);
      if (!response.ok) throw new Error('Cliente no encontrado');
      const data = await response.json();
      setClientes([data]); 
      setError('');
    } catch (err: any) {
      setClientes([]);
      setError(err.message || 'Error al buscar cliente');
    }
  };

  
  const manejarKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      buscarCliente();
    }
  };

  return (
    <main className="clientes-container">
      <div className="header-clientes">
        <h2>Clientes</h2>
        <button className="nuevo-btn">Nuevo</button>
      </div>

      <input
        type="text"
        placeholder="Buscar por ID"
        className="buscar-input"
        value={busqueda}
        onChange={(e) => setBusqueda(e.target.value)}
        onKeyDown={manejarKeyDown}
      />

      {error && <p style={{ color: 'red' }}>{error}</p>}

      <div className="tabla-clientes">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Identificación</th>
              <th>Dirección</th>
              <th>Teléfono</th>
              <th>Género</th>
              <th>Edad</th>
              <th>Usuario</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            {clientes.length === 0 ? (
              <tr>
                <td colSpan={9} className="sin-datos">No hay resultados</td>
              </tr>
            ) : (
              clientes.map((cliente) => (
                <tr key={cliente.id}>
                  <td>{cliente.id}</td>
                  <td>{cliente.nombre}</td>
                  <td>{cliente.identificacion}</td>
                  <td>{cliente.direccion}</td>
                  <td>{cliente.telefono}</td>
                  <td>{cliente.genero}</td>
                  <td>{cliente.edad}</td>
                  <td>{cliente.nombreUsuario}</td>
                  <td>{cliente.estado}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </main>
  );
};

export default ClienteList;
