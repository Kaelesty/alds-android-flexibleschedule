import {useEffect, useState} from "react";
import {Button} from "reactstrap";


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
    
    return (
        <>
        {Props.Codes.map(code =>(
            <p>{code.code}
            <Button onClick ={()=>Delete(code)}>Удалить</Button>
            </p>
            
        ))}
        </>
    );
};

export default Codes;
