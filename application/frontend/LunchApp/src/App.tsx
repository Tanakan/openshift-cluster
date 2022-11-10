import React, { StrictMode, useEffect, useState } from 'react';
import './App.css';
import { BrowserRouter,  Route, Routes } from 'react-router-dom';
import Menu from './containers/MenuContainer';

import UserInfo from './containers/UserInfoContainer';
import { useKeycloak } from "@react-keycloak/web";
import Header from "./containers/common/HeaderContainer";
import LunchHistory from "./containers/LunchHistoryContainer";
import { UserProfile } from "./models/UserProfile";


import Keycloak from 'keycloak-js';
import { ReactKeycloakProvider } from '@react-keycloak/web'
import { HomePage } from './pages/HomePage';

function App() {
  
  const authClient = new Keycloak('./keycloak.json');

  return (
    <ReactKeycloakProvider
      initOptions={{ onLoad: 'login-required' }}
      authClient={authClient}
    >
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path='/menu' element={<Menu />}></Route>
                <Route path='/history' element={<LunchHistory />}></Route>
                <Route path='/userInfo' element={<UserInfo />}></Route>
            </Routes>
        </BrowserRouter>
    </ReactKeycloakProvider>
  );
}

export default App;
