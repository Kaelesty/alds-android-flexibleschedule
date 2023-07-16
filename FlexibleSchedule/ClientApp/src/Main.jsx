import React, {SyntheticEvent, useEffect, useState} from 'react';
import {Navigate} from "react-router-dom";
import Header from "./Header/header__navigation";

// const TimeTable = [ //todo Будет fetch с методом post для получения расписания,но пока так
//     [['время','пара','место','препод'],['время','пара','место','препод'],['время','пара','место','препод'],['время','пара','место','препод'],['время','пара','место','препод'],['время','пара','место','препод'],['время','пара','место','препод']],
//     [["1","2","3","4"],[]],
//     [1,2,3,5]
//    
// ]
const MainPage = (props) => {
    const [TimeTable, setTimeTable] = useState([]);

    useEffect(()=>{
        fetch("api/Group/GetFullTimeTable")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                setTimeTable(responseJson)
            })
    },[])
    const [name, setName] = useState('');


    console.log(name)
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
            <table>
                <tr>
                    <th>Понедельник</th>
                    <th>Вторник</th>
                    <th>Среда</th>
                    <th>Четверг</th>
                    <th>Пятницу</th>
                    <th>Суббота</th>
                    <th>Воскресение</th>
                </tr>
                {TimeTable.map(days =>(
                <td>
                    {days.map(pair =>(
                        pair[0] == '-'? pair = '' : pair = pair,
                            pair[1] == '-'? pair = '' : pair = pair,
                            pair[3] == '-'? pair = '' : pair = pair,
                            pair[4] == '-'? pair = '' : pair = pair,
                            pair != ''  ? <tr>{pair[0]}, {pair[1]},{pair[2]}, {pair[3]}</tr> : ''
                        ))}
                </td>
                    ))}
            </table>
        </div>
        
    );
}

export default MainPage;
