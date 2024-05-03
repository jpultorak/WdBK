import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LogoutIcon from '@mui/icons-material/Logout';
import {
  AppBar,
  IconButton,
  Menu,
  MenuItem,
  Stack,
  Toolbar,
  Typography,
} from '@mui/material';
import { useState } from 'react';
import GlowingButton from './GlowingButton';

export default function NavigationBar() {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const username = 'Janek';

  function handleMenuClose() {
    setAnchorEl(null);
  }
  function handleMenuClick(event: React.MouseEvent<HTMLElement>) {
    setAnchorEl(event.currentTarget);
  }
  function handleLogoutClick() {}

  return (
    <AppBar position="sticky">
      <Toolbar>
        <Stack direction="row" spacing={2} alignItems="center">
          <GlowingButton>
            <Typography variant="body2">Dashboard</Typography>
          </GlowingButton>

          <GlowingButton>
            <Typography variant="body2">Info</Typography>
          </GlowingButton>
        </Stack>

        <Stack direction={'row'} justifyContent="end" flexGrow={1}>
          <IconButton onClick={handleMenuClick}>
            <AccountCircleIcon />
          </IconButton>
        </Stack>

        <Menu
          sx={{ mt: '30px' }}
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={handleMenuClose}
          anchorOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          keepMounted
          transformOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
        >
          <MenuItem sx={{ minWidth: 200 }}>
            <Stack direction="row" gap={2}>
              <AccountCircleIcon />
              <Typography>{username}</Typography>
            </Stack>
          </MenuItem>

          <MenuItem onClick={handleLogoutClick}>
            <Stack direction="row" gap={2}>
              <LogoutIcon />
              <Typography>Logout</Typography>
            </Stack>
          </MenuItem>
        </Menu>
      </Toolbar>
    </AppBar>
  );
}
