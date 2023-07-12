import React, {SyntheticEvent, useState} from 'react';
import {Navigate} from "react-router-dom";
import Header from "./Header/header__navigation";

const TimeTable = [ //todo Будет fetch с методом post для получения расписания,но пока так
    [1,2,3,4],
    [1,2,3,4],
    [1,2,3,5]
    
]

const MainPage = (props) => {
    const [name, setName] = useState('');


    console.log(name)
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
            <table>
                <tr>
                    <th>номер пары</th>
                    <th>Название пары</th>
                    <th>место</th>
                    <th>препод</th>
                </tr>
                {TimeTable.map(Pair =>
                    <tr>
                        <td>{Pair[0]}</td>
                        <td>{Pair[1]}</td>
                        <td>{Pair[2]}</td>
                        <td>{Pair[3]}</td>
                    </tr>
                )}
                
            </table>
        </div>
        
    );
}

export default MainPage;
