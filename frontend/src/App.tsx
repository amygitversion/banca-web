import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Sidebar from './components/Sidebar';
import ClientesPage from './pages/ClientesPage';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app">
        <Header />
        <div className="main">
          <Sidebar />
          <div className="content">
            <Routes>
              <Route path="/" element={<ClientesPage />} />          
              {/* <Route path="/cuentas" element={<CuentasPage />} /> */}
              {/* <Route path="/movimientos" element={<MovimientosPage />} /> */}
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
}

export default App;
