import { useEffect } from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import StartPages from './pages/startPage/startPage';
import LoginPage from './pages/loginPage/loginPage';

function App() {
  /*if (localStorage.getItem('user') == undefined) {
    localStorage.setItem('user', {name: undefined, group: []})
  }*/
  /*useEffect(() => {
    fetch("http://localhost:8080/")
    .then(res => res.json())
    .then((result) => {
      console.log(result)
    })
    .catch(error => {
      console.log('error')
    })
  }, [])*/
  return (
    <Routes>
      <Route path="/" element={<StartPages/>}></Route>
      <Route path="/login" element={<LoginPage/>}></Route>
    </Routes>
  );
}

export default App;
