import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import { Link } from 'react-router-dom';

export default function HeaderMenues() {
  return (
    <div style={{ paddingBottom: '3%' }}>
      <Toolbar disableGutters sx={{ bgcolor: '#EEEEEE' }}>
        <Link to="/menu">
          <Button sx={{ color: 'black' }}>メニュー</Button>
        </Link>
        <Link to="/history">
          <Button sx={{ color: 'black' }} >利用状況参照</Button>
        </Link>
        <Link to="/userInfo">
          <Button sx={{ color: 'black' }}>決済情報確認</Button>
        </Link>

      </Toolbar>
    </div>
  );
}