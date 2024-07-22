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

  const token = sessionStorage.getItem('token');

  console.log('Getting token from sessionStorage:', token);
  if (token) {
    dispatch(setCredentials({ token: token }));
  }

  // const sessionCount = sessionStorage.getItem('sessionCount');
  // const allowTab = !sessionCount || parseInt(sessionCount) > 1;

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="*" element={<NotFound />} />

        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/info" element={<Info />} />
      </Routes>
    </Router>
  );
}

export default App;
