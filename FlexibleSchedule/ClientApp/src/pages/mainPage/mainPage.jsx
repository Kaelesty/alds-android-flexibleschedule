import React, { SyntheticEvent, useEffect, useState } from 'react';
import MainPage__main from '../elemOnPage/mainPage__main/mainPage__main';
import Header from '../../Header/header__navigation';

const MainPage = (props) => {

    const [TimeTable, setTimeTable] = useState();
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
    if (TimeTable != undefined) {
        return (
            <>
            <Header name={props.name} setName={props.setName} />
            <MainPage__main mas={TimeTable} />
            </>
            
        );
    } else {
        return (
            <h1>Расписаний не найдено</h1>
        )
    }

}

export default MainPage;
