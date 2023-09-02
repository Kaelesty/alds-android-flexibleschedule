import React, {SyntheticEvent, useState} from 'react';
import {Navigate} from "react-router-dom";
import "./Auth.css";


const Login = (props) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [redirect, setRedirect] = useState(false);

    const submit = async (e) => {
        e.preventDefault();

        const response = await fetch('api/Auth/Login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                email,
                password
            })
        });
        const content = await response.json();
        props.setUser({name:content.name, isAuthorized: true});
        setRedirect(true);
    }

    if (redirect) {
        return <Navigate to="/" />;
    }
    return (
        <form onSubmit={submit}  className={"formAuth"}>
            <h1 className="h3 mb-3 fw-normal text-center" >Please sign in</h1>
            <input type="email" className="form-control" placeholder="Email address" required
                   onChange={e => setEmail(e.target.value)}
            />

            <input type="password" className="form-control" placeholder="Password" required
                   onChange={e => setPassword(e.target.value)}
            />

            <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        </form>
    );
};

export default Login;
