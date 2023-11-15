import "./loginPage.css"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

const LoginPage = (props) => {
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const login = (event) => {
        console.log('a', userName, password)
        fetch("http://localhost:8080/login?" + "&user=" + userName + "&password=" + password)
            .then((res) => res.json())
            .then((result) => {
                if (result.state) {
                    localStorage.setItem('user', JSON.stringify(result.user))
                    console.log(JSON.parse(localStorage.getItem('user')))
                    navigate('/')
                } else {
                    alert("Логин или пароль не верный")
                }
            })
            .catch((error) => {
                console.log(error)
            });
    }
    return (
        <Container>
            <Row className="justify-content-md-center mt-5">
                <Col className="col-xll-3 col-xl-4 col-lg-6 col-md-9 col-sm-12">{/* sm, md, lg, xl, xxl */}
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" onChange={(event) => { setUserName(event.target.value) }} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Password" onChange={(event) => { setPassword(event.target.value) }} />
                        </Form.Group>
                        <Button type="submit"  variant="outline-light" onClick={login}>
                            Submit
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}

export default LoginPage