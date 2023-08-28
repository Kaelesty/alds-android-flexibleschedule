import React, { SyntheticEvent, useEffect, useState } from 'react';
import MainPage__main from '../elemOnPage/mainPage__main/mainPage__main';
import Header from '../../Header/header__navigation';

const MainPage = (props) => {

    const [TimeTable, setTimeTable] = useState(undefined);
    useEffect(() => {
        fetch("api/Group/GetFullTimeTable")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                setTimeTable(responseJson)
            })
    }, [])
    if (TimeTable !== undefined) {
        return (
            <>
            <Header user={props.user} setUser={props.setUser} />
            <MainPage__main mas={TimeTable} />
            </>
            
        );
    } else {
        return (
            <h1>Loading...</h1>
        )
    }

}

export default MainPage;
