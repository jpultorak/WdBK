import { Button, styled } from '@mui/material';

const GlowingButton = styled(Button)(({ theme }) => ({
  position: 'relative',
  overflow: 'hidden',
  '&:hover': {
    backgroundColor: theme.palette.secondary.light,
    boxShadow: `0px 0px 10px 5px ${theme.palette.secondary.main}`,
  },
}));

export default GlowingButton;
