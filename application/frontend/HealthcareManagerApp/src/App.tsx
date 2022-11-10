import React, { StrictMode, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter,  Route, Routes } from 'react-router-dom';
import Header from './pages/components/Header';

import LaunchHistory from './containers/LaunchHistoryContainer';
import UserInfo from './containers/UserInfoContainer';
import Menues from './pages/components/Menues';

import Keycloak from 'keycloak-js';
import { useKeycloak, ReactKeycloakProvider } from '@react-keycloak/web'

function App() {
  
  const authClient = Keycloak('./keycloak.json');

  return (
    <ReactKeycloakProvider
      initOptions={{ onLoad: 'login-required' }}
      authClient={authClient}
    >
      <Header></Header>
        <BrowserRouter>
          <Menues></Menues>
            <Routes>
              <Route path='/launchHistory' element={<LaunchHistory/>}></Route>
              <Route path='/userInfo' element={<UserInfo/>}></Route>
            </Routes>
        </BrowserRouter>
    </ReactKeycloakProvider>

  );
}

export default App;
