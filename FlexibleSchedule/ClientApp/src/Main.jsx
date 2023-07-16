import React, {SyntheticEvent, useEffect, useState} from 'react';
import {Navigate} from "react-router-dom";
import style from './custom.css';
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
    const Infos = ['Пара','Предмет','Место','Преподаватель']
    const dayss = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресение']
    console.log(name)
    let i =-1
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
            <table st>
                {/*<tr>*/}
                {/*    <th>Понедельник</th>*/}
                {/*    <th>Вторник</th>*/}
                {/*    <th>Среда</th>*/}
                {/*    <th>Четверг</th>*/}
                {/*    <th>Пятницу</th>*/}
                {/*    <th>Суббота</th>*/}
                {/*    <th>Воскресение</th>*/}
                {/*</tr>*/}
                {TimeTable.map(days =>(
                    i++,
                    <tr>
                    <th>{dayss[i]}</th>
                <td>
                    <tr>
                        <th>{Infos[0]}</th>
                        <th>{Infos[1]}</th>
                        <th>{Infos[2]}</th>
                        <th>{Infos[3]}</th>
                    </tr>
                    {days.map(pair =>(
                    <tr>
                            <td>{pair[0]}</td><td>{pair[1]}</td><td>{pair[2]}</td><td>{pair[3]}</td> 
                    </tr>
                        ))}
                </td>
                </tr>
                    ))}
                <td>
                </td>
            </table>
        </div>
    );
}                        // pair[0] == '-'? pair = '' : pair = pair,
//     pair[1] == '-'? pair = '' : pair = pair,
//     pair[3] == '-'? pair = '' : pair = pair,
//     pair[4] == '-'? pair = '' : pair = pair,

export default MainPage;
