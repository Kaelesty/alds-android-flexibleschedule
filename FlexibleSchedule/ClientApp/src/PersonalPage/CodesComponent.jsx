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
        let isValid = true
        let codes = new Map();
        const MainForm = document.getElementById('form')
        let arrayWithPriority = []
        console.log("len",MainForm.childNodes.length -1)
        for(let i =0;i<MainForm.childNodes.length -1;i++) {
            let value = MainForm.childNodes[i].childNodes[3].childNodes[0].value
            let key = MainForm.childNodes[i].childNodes[3].childNodes[0].id
            let code = MainForm.childNodes[i].childNodes[1].nodeValue
            if(value <1 || arrayWithPriority.includes(value)){

                alert("Приоритеты должны быть больше 0 и не должны повторяться")
                isValid = false
            }
            arrayWithPriority.push(value)

        }
        if(isValid)
        for(let i =0;i<MainForm.childNodes.length -1;i++) {
        let value = MainForm.childNodes[i].childNodes[3].childNodes[0].value
        let key = MainForm.childNodes[i].childNodes[3].childNodes[0].id
        let code = MainForm.childNodes[i].childNodes[1].nodeValue

        if(isValid){
            fetch('api/Group/ChangePriority', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',
                body: JSON.stringify({
                    GroupId: key,
                    Code: code,
                    priority: value,
                })
            });       
        }
            
        }
        GetCodes()
    }
    return (
        <>
            <form id={'form'} onSubmit={PriorityHandler}>
        {Props.Codes.map(code =>(
            <span onSubmit={PriorityHandler} className="code">
                Код группы: {code.code} Приоритет - 
                
                <MyInput Code={code}></MyInput>
                Текущий приоритет - {code.priority}
            <Button  onClick ={()=>Delete(code)}>Удалить</Button>

            </span>
            
            
        ))}
            <button type={"submit"}>Сохранить приоритеты</button>
            </form>
        </>
    );
};
export default Codes;

const MyInput = ({Code}) => {
    const [code, setCode] = useState();
    return (
        <div>
            <input
                className="input"
                type={"number"}
                id={Code.groupId}
                value={code}
                onChange={({ target: { value } }) =>value > 0  ? setCode(value) : setCode("")}
            />
        </div>
    );
}

