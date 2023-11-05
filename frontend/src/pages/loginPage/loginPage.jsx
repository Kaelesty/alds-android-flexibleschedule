import { useState } from "react"
import "./loginPage.css"
import { redirect, useNavigate } from "react-router-dom"

const LoginPage = (props) => {
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const login = (event) => {
        //userName = event.target
        console.log()
        fetch("http://localhost:8080/?type=login" + "&user=" + userName + "&password=" + password)
        .then((res) => res.json())
        .then((result) => {
            console.log(result)
            localStorage.setItem('user', result)
            console.log(localStorage.getItem('user'))
        })
        .catch((error) => {
            console.log(error)
        })
        /*navigate('/')*/
    }
    return (
        <div>
            <div>
                <input type="email" onChange={(event) => {setUserName( event.target.value)}}></input>
                <input type="password" onChange={(event) => {setPassword( event.target.value)}}></input>
                <input type="submit" onClick={login}></input>
            </div>
        </div>
    )
}

export default LoginPage