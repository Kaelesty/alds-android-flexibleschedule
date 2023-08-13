import React, { SyntheticEvent, useEffect, useState } from 'react';
//import {Navigate} from "react-router-dom";
import style from './MainPage.module.css';
import Header from "../Header/header__navigation";
import Day from './day/day';
const MainPage = (props) => {

    const [TimeTable, setTimeTable] = useState();
    const [name, setName] = useState('');

    useEffect(() => {
        fetch("api/Group/GetFullTimeTable")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                setTimeTable(responseJson)
            })
    }, [])
    console.log("mas", TimeTable)
    const PairInfoTitle = ['Пара', 'Предмет', 'Место', 'Преподаватель']
    const DaysOfWeek = ['Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота', 'Воскресение']
    if (TimeTable != undefined) {
        return (
            <>
            <Header name={props.name} setName={props.setName} />
            <div className={style.mainPage}>
                {<Day days={TimeTable.days} />}
            </div>
            </>
            
        );
    }

}

export default MainPage;
