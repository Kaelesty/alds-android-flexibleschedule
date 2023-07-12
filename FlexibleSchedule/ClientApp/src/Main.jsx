import React, {SyntheticEvent, useState} from 'react';
import {Navigate} from "react-router-dom";
import Header from "./Header/header__navigation";

const MainPage = (props) => {
    const [name, setName] = useState('');


    console.log(name)
    return (
        <div >
            <Header name={props.name} setName={props.setName} />
        </div>
        
    );
}

export default MainPage;
