import { useEffect, useMemo, useState } from "react";
import MenuService from "../services/MenuService";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import { LunchHistory } from "../models/LunchHistory";

type Props = {
    lunchHistories:LunchHistory[]
}
export default function LunchHistoryPage(props: Props){
  return(
    <Container>
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell align="center">利用日時</TableCell>
            <TableCell align="center">注文内容</TableCell>
            <TableCell align="center">栄養素(kcal)</TableCell>
            <TableCell align="center">金額(円)</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.lunchHistories.map((lunchHistory : LunchHistory) => (
            <TableRow
              key={lunchHistory.id.toString()}
            >
              <TableCell align="center">{lunchHistory.createdAt.toDateString()}</TableCell>
              <TableCell align="center">{lunchHistory.menu.name}</TableCell>
              <TableCell align="center">{lunchHistory.menu.tuition.cal}</TableCell>
              <TableCell align="center">{lunchHistory.price}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  </Container>
  )
}