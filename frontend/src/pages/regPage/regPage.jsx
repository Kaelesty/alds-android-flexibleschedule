import "./regPage.css"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Button, Col, Container, Form, InputGroup, Row, Alert } from 'react-bootstrap';

const RegPage = (props) => {
    const [login, setLogin] = useState('')
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const registration = (event) => {
        event.preventDefault(); // отключаем отправку формы
        if (userName == '' || password == '' || login == '') {
            if (document.getElementsByClassName('alertForm').length == 0) {
                document.getElementsByTagName('Button')[0].insertAdjacentHTML('beforebegin', '<p class="alertForm">Заполните все поля формы!</p>')
            }
            return false
        }
        console.log('a', userName, password)
        fetch("http://localhost:8080/registration?" + "&user=" + userName + "&password=" + password + "&login=" + login)
            .then((res) => res.json())
            .then((result) => {
                if (result.state) {
                    localStorage.setItem('user', JSON.stringify(result.user))
                    console.log(JSON.parse(localStorage.getItem('user')))
                    navigate('/')
                } else {
                    alert("Такой пользоваьель уже существует")
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
                        <Form.Label>Имя пользователя</Form.Label>
                        <InputGroup className="mb-3">
                            <InputGroup.Text id="basic-addon1">@</InputGroup.Text>{/**/}
                            <Form.Control
                                placeholder="Username"
                                aria-label="Username"
                                aria-describedby="basic-addon1"
                                onChange={(event) => { setLogin(event.target.value) }}
                            />
                        </InputGroup>{/**/}
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Почта</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" onChange={(event) => { setUserName(event.target.value) }} />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Пароль</Form.Label>
                            <Form.Control type="password" placeholder="Password" onChange={(event) => { setPassword(event.target.value) }} />
                        </Form.Group>
                        <Button type="submit" variant="outline-light" onClick={registration}>
                            Submit
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}

export default RegPage