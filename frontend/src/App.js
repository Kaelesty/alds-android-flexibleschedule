import { useEffect } from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import StartPages from './pages/startPage/startPage';
import LoginPage from './pages/loginPage/loginPage';
import RegPage from './pages/regPage/regPage';
import MainPage from './pages/mainPage/mainPage';

function App() {
  if (localStorage.getItem('user')==undefined) {
    localStorage.setItem('user', undefined)
  }
  return (
    <Routes>
      <Route path="/" element={(localStorage.getItem('user')==undefined) ? <StartPages/> : <MainPage/>}></Route>
      <Route path="/login" element={<LoginPage/>}></Route>
      <Route path="/registration" element={<RegPage/>}></Route>
    </Routes>
  );
}

export default App;
