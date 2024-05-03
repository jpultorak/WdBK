import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#ff007f', // Cyberpunk pink
    },
    secondary: {
      main: '#00ffbf', // Cyberpunk teal
    },
    action: {
      disabled: '#ff007f',
    },
    background: {
      default: '#121212', // Dark background
      paper: '#1e1e1e',
    },
    text: {
      primary: '#ffffff',
      secondary: '#b3b3b3',
    },
  },
  typography: {
    fontFamily: 'Roboto, sans-serif',
    h1: {
      color: '#ffffff',
    },
  },
});

// const cyberpunkBackground = keyframes`
//     0% { background-position: 0% 50%; }
//     50% { background-position: 100% 50%; }
//     100% { background-position: 0% 50%; }
// `;
// export function CyberpunkBackground({ children: React.ReactNode }) {
//   return (
//     <Box
//       sx={{
//         width: '100vw',
//         height: '100vh',
//         overflow: 'hidden',
//         position: 'relative',
//         background: 'linear-gradient(270deg, #ff4dff, #4dffff)',
//         backgroundSize: '400% 400%',
//         animation: `${cyberpunkBackground} 15s ease infinite`,
//       }}
//     >
//       {children}
//     </Box>
//   );
// }
export default theme;
