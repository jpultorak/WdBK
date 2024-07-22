import {
  Avatar,
  Box,
  Button,
  Grid,
  Link,
  TextField,
  Typography,
} from '@mui/material';

import { Link as RouterLink, useNavigate } from 'react-router-dom';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useAuthenticateMutation } from '../services/apiSlice';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { setCredentials } from '../services/authSlice';
import {
  decreaseTabCount,
  getTabCount,
  increaseTabCount,
} from '../util/sesssion';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [login] = useAuthenticateMutation();

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      const { data } = await login({ email, password });
      console.log('LOGIN RESPONSE DATA:', data);

      if (data && data.jwtToken) {
        const tabCount = getTabCount();
        if (tabCount >= 1) {
          window.alert('Too many open tabs!');
          throw Error('Too many open tabs');
        }
        increaseTabCount();
        window.addEventListener('beforeunload', () => {
          decreaseTabCount();
        });
        dispatch(setCredentials({ token: data.jwtToken }));
        sessionStorage.setItem('token', data.jwtToken);

        navigate('/dashboard');
      } else throw Error('Empty jwt response');
    } catch (err) {
      console.error('Failed to login: ', err);
    }
  };

  return (
    <Box
      sx={{
        marginTop: 15,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
        <LockOutlinedIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        Sign in
      </Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
        <TextField
          margin="normal"
          required
          fullWidth
          id="email"
          label="Email Address"
          name="email"
          autoComplete="email"
          autoFocus
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <TextField
          margin="normal"
          required
          fullWidth
          name="password"
          label="Password"
          type="password"
          id="password"
          autoComplete="current-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          Sign In
        </Button>
        <Grid container>
          <Grid item xs>
            <Link href="#" variant="body2">
              Forgot password?
            </Link>
          </Grid>
          <Grid item>
            <Link component={RouterLink} to={'/sign-up'}>
              <Typography variant="body2">
                Don't have an account? Sign Up
              </Typography>
            </Link>
          </Grid>
        </Grid>
      </Box>
    </Box>
  );
}
