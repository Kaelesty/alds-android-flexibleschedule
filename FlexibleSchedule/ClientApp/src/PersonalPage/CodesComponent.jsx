import {useEffect, useState} from "react";
import {Button} from "reactstrap";


const Codes = () => {
    const [Codes, setCodes] = useState([]);
    const Delete = (props)=> {
        fetch('api/Group/DeleteGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                GroupId: props.groupId,
                Code: props.code
            })
        });

    }
    useEffect(()=>{
        fetch("api/Group/GetAllGroupCodes")
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                setCodes(responseJson)
            })
    },[Delete])
    const [code,setCode] = useState()
    
    console.log(Codes)
    return (
        <>
        {Codes.map(code =>(
            <p>{code.code}
            <Button onClick ={()=>Delete(code)}>Удалить</Button>
            </p>
            
        ))}
        </>
    );
};

export default Codes;
