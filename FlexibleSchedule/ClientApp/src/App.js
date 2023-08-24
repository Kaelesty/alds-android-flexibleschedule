import React, {Component, useEffect, useState} from 'react';
import { Route, Routes } from 'react-router-dom';
import './app.css';
import RegisterPage from "./Auth/RegisterPage";
import LoginPage from "./Auth/LoginPage";
import MainPage from "./pages/mainPage/mainPage";
import {PersonalPageMain} from "./PersonalPage/PersonalPageMain";
import MyTimeTable from "./PersonalPage/MyTimeTablesPage";
import StartPage from './pages/startPage/startPage';
function App() {

    const [user, setUser] = useState({name: null,
    isAuthorized: null});
    useEffect( () => {
    (
        async () => {
            const response = await fetch('api/Auth/User', {
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',

            });
            if(response.status === 200){
                const content = await response.json();
                setUser({name: content.name, isAuthorized: true});
            }

            else{
                setUser({name: null, isAuthorized: false});
                
            }
        }
    )();
        
},[]);
    return (
        <Routes>
            <Route path="/PersonalPageMain" element={<PersonalPageMain user={user} setUser={setUser}/>}/>
            <Route path="/MyTimeTables" element={<MyTimeTable user={user} setUser={setUser}/>}/>
            <Route path="/register" element={<RegisterPage />}/>
            <Route path="/login" element={<LoginPage setUser={setUser}/>}/>
            <Route path="/" element={(user.isAuthorized) ? <MainPage user={user} setUser={setUser}/> : <StartPage user={user} setUser={setUser}/>}/>

        </Routes>)
  
}
export default App;

