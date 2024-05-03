import { Box, Container, Paper, TextField, Typography } from '@mui/material';
import NavigationBar from '../components/NavigationBar';
import GlowingButton from '../components/GlowingButton';

export default function Info() {
  const email = 'example@gmail.com';
  const firstName = 'Janek';
  const lastName = 'Franek';

  function handlePasswordReset() {
    console.log('Reset Password clicked');
  }
  return (
    <>
      <NavigationBar />
      <Container maxWidth="sm" sx={{ mt: 5 }}>
        <Paper elevation={3} sx={{ padding: 3 }}>
          <Box
            sx={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              gap: 2,
            }}
          >
            <Typography variant="h4" gutterBottom>
              User Information
            </Typography>

            <TextField
              disabled
              label="First Name"
              value={firstName}
              fullWidth
            />
            <TextField disabled label="Last Name" value={lastName} fullWidth />
            <TextField disabled label="Email" value={email} fullWidth />

            <GlowingButton onClick={handlePasswordReset}>
              <Typography>I forgot my password</Typography>
            </GlowingButton>
          </Box>
        </Paper>
      </Container>
    </>
  );
}
