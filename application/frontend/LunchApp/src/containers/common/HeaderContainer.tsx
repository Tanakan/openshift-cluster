import { useKeycloak } from "@react-keycloak/web";
import { useEffect, useState } from "react";
import { UserProfile } from "../../models/UserProfile";
import Header from "../../pages/common/Header";
import UserProfileService from "../../services/UserProfileService";

export default function HeaderContainer(){
    const {keycloak, initialized} = useKeycloak();
    const [userProfile, setUserProfile] = useState<UserProfile>(
        {employeeId:'',name:'',mailAddress:''}
    );
    const userProfileService: UserProfileService = new UserProfileService(keycloak.token!);

    useEffect(()=>{
      load();
    },[initialized])

    async function load() {
      const response = await userProfileService.getUserProfile(keycloak.idTokenParsed?.preferred_username);
      setUserProfile(response);
      console.log(keycloak);
    }
    
    return (
        <Header userProfile={userProfile} />
    )
    
}