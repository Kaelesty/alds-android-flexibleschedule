import {useEffect, useState} from "react";
import {Navigate} from 'react-router-dom';


const MyTimeTable = () => {
    const [redirect, setRedirect] = useState(false);

    const [code,setCode] = useState()
    const submit = async (e) => {
        e.preventDefault();
        await fetch('/api/Group/ConnectToGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                code,
            })
            
        });
        setRedirect(true);
    }
    if (redirect) {
        return <Navigate to="/"/>;
    }
    

    return (
        <form onSubmit={submit}>
            <h1 className="h3 mb-3 fw-normal">Введите код группы</h1>

            <input className="form-control" placeholder="Code" required
                   onChange={e => setCode(e.target.value)}
            />
            <button className="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
        </form>
    );
};

export default MyTimeTable;
