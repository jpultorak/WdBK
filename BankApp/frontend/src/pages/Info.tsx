import { Box, Container, Paper, TextField, Typography } from '@mui/material';
import NavigationBar from '../components/NavigationBar';
import GlowingButton from '../components/GlowingButton';
import { useGetUserInfoQuery } from '../services/apiSlice';

export default function Info() {
  const { data, isLoading } = useGetUserInfoQuery();

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
            {isLoading || !data ? (
              <>Loading ... </>
            ) : (
              <>
                <TextField
                  disabled
                  label="First Name"
                  value={data.firstName}
                  fullWidth
                />
                <TextField
                  disabled
                  label="Last Name"
                  value={data.lastName}
                  fullWidth
                />
                <TextField
                  disabled
                  label="Email"
                  value={data.email}
                  fullWidth
                />

                <GlowingButton onClick={handlePasswordReset}>
                  <Typography>I forgot my password</Typography>
                </GlowingButton>
              </>
            )}
          </Box>
        </Paper>
      </Container>
    </>
  );
}
