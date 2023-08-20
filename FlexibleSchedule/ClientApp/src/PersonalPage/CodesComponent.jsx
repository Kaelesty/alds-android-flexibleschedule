import React, {useEffect, useState} from "react";
import {Button, Input} from "reactstrap";
import style from "./styles.css"

const Codes = (Props) => {
    const [Codes, setCodes] = useState([]);
    const GetCodes = () => {
        fetch("api/Group/GetAllGroupCodes")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                Props.setCodes(responseJson)
            })
    }
    const Delete = async (props)=> {
        
        await fetch('api/Group/DeleteGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                GroupId: props.groupId,
                Code: props.code
            })
        });
        await GetCodes()
    }
    useEffect(()=>{
        fetch("api/Group/GetAllGroupCodes")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                Props.setCodes(responseJson)
            })
    },[])
    const [code,setCode] = useState()
    const PriorityHandler = (event)=>{
        event.preventDefault()
        let codes = new Map();
        const MainForm = document.getElementById('form')
        let arrayWithPriority = []
        for(let i =0;i<MainForm.childNodes.length - 1;i++) {
            let value = MainForm.childNodes[i].childNodes[3].value
            let key = MainForm.childNodes[i].childNodes[3].id
            if(value <1 || arrayWithPriority.includes(value)){
                alert("Приоритеты должны быть больше 0 и не должны повторяться")
                break
            }
                arrayWithPriority.push(value)
                codes.set(key, value)
        }
        console.log(JSON.stringify({
            codes
        }))
        fetch('api/Group/ChangePriority', {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                CodesD: codes.toString()
            })
        });
        GetCodes()
    }
    return (
        <>
            <form id={'form'} onSubmit={PriorityHandler}>
        {Props.Codes.map(code =>(
            <span onSubmit={PriorityHandler} className="code">
                Код группы: {code.code} Приоритет - 
                
                <Input id={code.groupId} className="input" type={"number"} placeholder={code.priority}></Input>
            <Button  onClick ={()=>Delete(code)}>Удалить</Button>

            </span>
            
            
        ))}
            <button type={"submit"}>Сохранить приоритеты</button>
            </form>
        </>
    );
};

export default Codes;
