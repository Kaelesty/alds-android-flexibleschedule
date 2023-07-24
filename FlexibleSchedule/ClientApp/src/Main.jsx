import React, {SyntheticEvent, useEffect, useState} from 'react';
import {Navigate} from "react-router-dom";
import style from './custom.css';
import Header from "./Header/header__navigation";
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
    console.log(TimeTable)
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
            <table st>
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
                        {
                            pair[0] == "-" & pair[1] == "-"&pair[2] == "-"&pair[3] == "-" ? null :<><td>{pair[0]}</td><td>{pair[1]}</td><td>{pair[2]}</td><td>{pair[3]}</td></>
                        
                        }
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
}                        

export default MainPage;
