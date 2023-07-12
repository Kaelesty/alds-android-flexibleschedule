import Header from "../Header/header__navigation";
import {useState} from "react";

export const PersonalPageMain = (props) =>{
    
    
const [name, setName] = useState('');


console.log(name)
return (
    < >
        <Header name={props.name} setName={props.setName} />
        <div>
            Hello {props.name}
            
        </div>
    </>

);
}
