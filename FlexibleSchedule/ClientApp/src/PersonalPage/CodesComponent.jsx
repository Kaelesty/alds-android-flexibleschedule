import React, {useEffect, useState} from "react";
import "./styles.css"
import {Button} from "reactstrap";

const Codes = (Props) => {
    
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
        GetCodes()
    },[])
    const [code,setCode] = useState()
    const PriorityHandler = (event)=>{
        event.preventDefault()
        for(let i =0;i<Props.Codes.length;i++) {
            let value = Props.Codes[i].priority
            let key = Props.Codes[i].groupId
            let code = Props.Codes[i].code
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
    
    const [currentCard,setCurrentCard] = useState(null)
    function dragStartHandler(e,code){
        setCurrentCard(code)
    }
    function dragLeaveHandler(e){
        e.target.style.background = '#000000'

    }
    function dragEndHandler(e,code){
        e.target.style.background = '#000000'

    }
    function dragOverHandler(e){
        e.preventDefault()
        e.target.style.background = 'lightgray'

    }
    function dropHandler(e,code){
        e.preventDefault()
        e.target.style.background = '#000000'

        Props.setCodes(Props.Codes.map(c=>{
            if(c.groupId===code.groupId){
                return {...c, priority: currentCard.priority}
            }
            if(c.groupId===currentCard.groupId){
                return {...c, priority: code.priority}
            }
            return c
        }))
    }
    const sortCard = (a,b) =>{
        if(a.priority>b.priority){
            return 1
        }
        else {
            return -1
        }
    }
    return (
        <form id={'form'} onSubmit={PriorityHandler}>
            {Props.Codes.sort(sortCard).map(code =>(
                <span
                    onDragStart={(e)=>dragStartHandler(e,code)}
                    onDragEnd={(e)=>dragEndHandler(e)}
                    onDragOver={(e)=>dragOverHandler(e)}
                    onDragLeave={(e)=>dragLeaveHandler(e)}
                    onDrop={(e)=>dropHandler(e,code)}
                    onDragEnd={(e)=>PriorityHandler(e)}
                    draggable={true}
                    onSubmit={PriorityHandler} className="code">
                    Код группы: {code.code}
                    <Button  onClick ={()=>Delete(code)}>Удалить</Button>
                </span>
                
            ))}
            <button type={"submit"}>Сохранить приоритеты</button>
        </form>
    );
};
export default Codes;


