import { useEffect, useState } from "react";
import { Menu } from "../models/Menu";
import MenuService from "../services/MenuService";
import PaymentHistoryPage from '../pages/LaunchHistoryPage'
import { LaunchHistory } from "../models/LaunchHistory";
import UserInfoPage from "../pages/UserInfoPage";

export default function UserInfoContainer(){

    return (
        <UserInfoPage />
    )
    
}