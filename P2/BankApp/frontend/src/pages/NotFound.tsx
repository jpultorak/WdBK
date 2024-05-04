import { keyframes } from '@emotion/react';
import { Button, Link, Typography } from '@mui/material';
import { Box, Container } from '@mui/system';

import { useTheme } from '@mui/material/styles';
import { Link as RouterLink } from 'react-router-dom';

function createGlowAnimation(color: string) {
  return keyframes`
      0%, 100% {
          text-shadow: 0 0 5px ${color}, 0 0 10px ${color}, 0 0 15px ${color}, 0 0 20px ${color};
      }
      50% {
          text-shadow: 0 0 10px ${color}, 0 0 20px ${color}, 0 0 30px ${color}, 0 0 40px ${color};
      }
  `;
}
const glitch = keyframes`
    0% {
        transform: skew(35deg, 0deg);
    }
    2%, 4%, 6% {
        transform: skew(-35deg, 0deg);
    }
    8%, 10%, 12% {
        transform: skew(15deg, 0deg);
    }
    14%, 16%, 18% {
        transform: skew(-15deg, 0deg);
    }
    20% {
        transform: skew(15deg, 0deg);
    }
    22% {
        transform: skew(0deg, 0deg);
    }
`;

export default function NotFound() {
  const theme = useTheme();
  const primaryGlow = createGlowAnimation(theme.palette.primary.main);
  const secondaryGlow = createGlowAnimation(theme.palette.secondary.main);

  return (
    <Container maxWidth="md" sx={{ textAlign: 'center', mt: 10 }}>
      <Box>
        <Typography
          variant="h1"
          color="primary"
          sx={{
            animation: `${glitch} 0.5s infinite alternate, ${primaryGlow} 1.5s infinite alternate`,
          }}
        >
          404
        </Typography>
        <Typography
          variant="h4"
          color="secondary"
          sx={{
            animation: `${glitch} 0.2s infinite alternate, ${secondaryGlow} 1.5s infinite alternate`,
          }}
        >
          Page Not Found
        </Typography>
      </Box>
      <Link component={RouterLink} to="/">
        <Button variant="contained" sx={{ mt: 3 }}>
          <Typography>Go Home</Typography>
        </Button>
      </Link>
    </Container>
  );
}
