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
import Container from '@mui/material/Container';

type Props = {
  menues: Menu[]
}

export default function MenuPage(props: Props) {

    return (
      <Container>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="center">メニュー名</TableCell>
                <TableCell align="center">エネルギー(kcal)</TableCell>
                <TableCell align="center">タンパク質(g)</TableCell>
                <TableCell align="center">糖質(g)</TableCell>
                <TableCell align="center">炭水化物(g)</TableCell>
                <TableCell align="center">価格(円)</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.menues.map((menu : Menu) => (
                <TableRow
                  key={menu.id.toString()}
                >
                  <TableCell align="center">{menu.name}</TableCell>
                  <TableCell align="center">{menu.tuition.cal}</TableCell>
                  <TableCell align="center">{menu.tuition.protein}</TableCell>
                  <TableCell align="center">{menu.tuition.salt}</TableCell>
                  <TableCell align="center">{menu.tuition.carbo}</TableCell>
                  <TableCell align="center">{menu.price}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    );
}