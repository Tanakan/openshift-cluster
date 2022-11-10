import { useKeycloak } from "@react-keycloak/web";
import { useEffect, useState } from "react";
import { LunchHistory } from "../models/LunchHistory";
import LunchHistoryPage from "../pages/LunchHistoryPage";
import LunchHistotyService from "../services/LunchHistoryService";

export default function HistoryContainer(){
    const [lunchHistories, setLunchHistories] = useState<LunchHistory[]>([]);
    const lunchHistotyService: LunchHistotyService= new LunchHistotyService();

    useEffect(()=>{
      load();
    },[])

    async function load() {
      const response = await lunchHistotyService.listLunchHistories();
      setLunchHistories(response);
    }
    return (
        <LunchHistoryPage lunchHistories={lunchHistories} />
    )
}