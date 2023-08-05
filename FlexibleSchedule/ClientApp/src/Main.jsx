import React, {SyntheticEvent, useEffect, useState} from 'react';
import {Navigate} from "react-router-dom";
import style from './custom.css';
import Header from "./Header/header__navigation";
const MainPage = (props) => {
    
    const [TimeTable, setTimeTable] = useState();
    const [name, setName] = useState('');

    useEffect(()=>{
        fetch("api/Group/GetFullTimeTable")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                setTimeTable(responseJson)
            })
    },[])
    
    const PairInfoTitle = ['Пара','Предмет','Место','Преподаватель']
    const DaysOfWeek = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресение']
    
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
            <div className={"TimTable"}> 
                Ваш код тут
            </div>
        </div>
    );
}                        

export default MainPage;
