import React, {Component, useEffect, useState} from 'react';
import { Route, Routes } from 'react-router-dom';
import './custom.css';
import RegisterPage from "./Auth/RegisterPage";
import LoginPage from "./Auth/LoginPage";
import MainPage from "./Main.jsx";
import {PersonalPageMain} from "./PersonalPage/PersonalPageMain";
import MyTimeTable from "./PersonalPage/MyTimeTablesPage";
function App() {

    const [name, setName] = useState(undefined);
    useEffect(() => {
    (
        async () => {
            const response = await fetch('api/Auth/User', {
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',

            });
            if(response.status === 200){

                const content = await response.json();
                setName(content.name);
            }

            else{
                setName(null);

            }
        }
    )();
});

    return (
        <Routes >PersonalPageMain
            <Route path="/PersonalPageMain" element={<PersonalPageMain name={name} setName={setName}/>}/> />
            MyTimeTable
            <Route path="/MyTimeTables" element={<MyTimeTable name={name} setName={setName}/>}/> />

            <Route path="/" element={<MainPage name={name} setName={setName}/>}/> />
            <Route path="/register" element={<RegisterPage />}/> />
            <Route path="/login" element={<LoginPage setName={setName}/>}/> />

        </Routes>)
  
}
export default App;

