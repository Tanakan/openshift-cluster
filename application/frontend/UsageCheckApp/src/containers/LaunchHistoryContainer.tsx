import { useEffect, useState } from "react";
import MenuService from "../services/MenuService";
import PaymentHistoryPage from '../pages/LaunchHistoryPage'
import { LaunchHistory } from "../models/LaunchHistory";
import { useKeycloak } from '@react-keycloak/web';
import LaunchHistoryService from "../services/LaunchHistoryService";
import axios from "axios";

export default function LaunchHistoryContainer(){
    const {keycloak, initialized} = useKeycloak();
    const [launchHistroies, setLaunchHistories] = useState<LaunchHistory[]>([]);
    const menuService: MenuService = new MenuService();
    const launchHistoryService:LaunchHistoryService = new LaunchHistoryService();

    useEffect(()=>{
      axios.get('http://employeeapp-3scale-apicast-staging.example.com/launchHisotory/launch_history',
        {
          headers: {'Authorization' : `Bearer ${keycloak.token}` }
        }).then(res => {
          setLaunchHistories(res.data);
      }).catch(err=>{
        if(err.response.status == 403){
          window.alert('権限が不足しています。')
        }
      })
    },[])

    async function load() {
      const response = await menuService.listMenues('');
      setLaunchHistories(response.data);
      console.log(launchHistroies);
    }
    
    return (

        <PaymentHistoryPage launchHistroies={launchHistroies} />
    )
    
}