import Header from "../Header/header__navigation";
import React, {useState} from "react";
import {SubmitHandler, useForm, UseFormHandleSubmit} from "react-hook-form";

export const PersonalPageMain = (props) => {
    //todo добавить проверку авторизирован ли пользователь
    const [code, setCode] = useState('');
    const [Day,setDay] = useState([])
    const { register, handleSubmit } = useForm({
        shouldUseNativeValidation: true,
    })
    const onSubmit = () =>{

    }
    return (
        < >
            <Header name={props.name} setName={props.setName}/>
            <form onSubmit={onSubmit}>
                <input ></input>
                <button type={"submit"}></button>
            </form>
        </>

    );
}

// fetch('api/Group/CreateGroup', {
//     method: 'POST',
//     headers: {'Content-Type': 'application/json'},
//     credentials: 'include',
//     body: JSON.stringify({
//         Code: code,
//
//     })
// })