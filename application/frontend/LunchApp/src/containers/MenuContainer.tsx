import { useEffect, useState } from "react";
import PaymentHistoryPage from '../pages/MenuPage'
import { useKeycloak } from '@react-keycloak/web';
import axios from "axios";
import { Menu } from "../models/Menu";
import MenuService from "../services/MenuService";
import { loadavg } from "os";

export default function MenuContainer(){
    const [menues, setMenues] = useState<Menu[]>([]);
    const menuService: MenuService = new MenuService();

    useEffect(()=>{
      load();
    },[])

    async function load() {
      const response = await menuService.listMenues();
      setMenues(response);
    }
    
    return (
        <PaymentHistoryPage menues={menues} />
    )
    
}