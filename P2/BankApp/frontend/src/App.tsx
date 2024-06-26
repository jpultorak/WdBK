import Dashboard from './pages/Dashboard';
import Info from './pages/Info';
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from 'react-router-dom';
import Login from './pages/Login';
import SignUp from './pages/SingUp';
import NotFound from './pages/NotFound';
import { useDispatch } from 'react-redux';
import { setCredentials } from './services/authSlice';

function App() {
  const dispatch = useDispatch();

  const token = localStorage.getItem('token');

  console.log('Getting token from localStorage:', token);
  if (token) {
    dispatch(setCredentials({ token: token }));
  }

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/info" element={<Info />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
