import { BrowserRouter, Route, Routes } from "react-router-dom";
import Menu from '../containers/MenuContainer';
import UserInfo from '../containers/UserInfoContainer';
import { useKeycloak } from "@react-keycloak/web";
import Header from "../containers/common/HeaderContainer";
import LunchHistory from "../containers/LunchHistoryContainer";
import { UserProfile } from "../models/UserProfile";

type Props = {
    userProfile: UserProfile;
}
  
export function HomePage() {

    return (
        <></>
    )
}