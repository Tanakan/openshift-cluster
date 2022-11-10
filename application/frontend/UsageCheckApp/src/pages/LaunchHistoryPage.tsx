import { useEffect, useMemo, useState } from "react";
import { Menu } from "../models/Menu";
import MenuService from "../services/MenuService";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { LaunchHistory } from "../models/LaunchHistory";
import Container from '@mui/material/Container';

type Props = {
  launchHistroies: LaunchHistory[]
}

export default function PaymentHistoryPage(props: Props) {

  return BasicTable()

  function BasicTable() {
    return (
      <Container>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="center">利用日</TableCell>
                <TableCell align="center">注文内容</TableCell>
                <TableCell align="center">金額</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.launchHistroies.map((row : LaunchHistory) => (
                <TableRow
                  key={row.id.toString()}
                >
                  <TableCell align="center">{row.createdAt}</TableCell>
                  <TableCell align="center">{row.menu.name}</TableCell>
                  <TableCell align="center">{row.menu.price}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    );
  }
}