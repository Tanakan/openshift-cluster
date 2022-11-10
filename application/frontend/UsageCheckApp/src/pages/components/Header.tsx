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
import { User } from '../../models/User';
import { useKeycloak } from '@react-keycloak/web'

type Props = {
  user?: User;
}

export default function Header(props: Props) {
  const {keycloak, initialized} = useKeycloak();
  return (
    <div>
      <Toolbar disableGutters sx={{ bgcolor: 'white' , justifyContent:'space-between'}}>
        <div>
          <Container sx={{display: 'flex'}}>
            <Typography
              variant="h3"
              noWrap
              component="div"
            >
              Odango Company
            </Typography>
            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{textAlign:'center'}}
            >
            </Typography>
          </Container>
        </div>
        {
        initialized ? (
            <Typography
            variant="subtitle1"
            noWrap
            component="div"
            align='inherit'
            sx={{ textAlign: 'right', mr: 2, display: { xs: 'none', md: 'flex' } }}
          >
            {keycloak.loadUserInfo}<br />
            {props.user?.mailAddress}<br />
            社員番号: {props.user?.employeeId}
          </Typography>
        ) : (<></>)
        }

      </Toolbar>
    </div>
  );
};
