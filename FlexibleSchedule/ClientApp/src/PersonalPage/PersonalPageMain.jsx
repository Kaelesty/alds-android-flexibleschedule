import Header from "../Header/header__navigation";
import React, {useState} from "react";

export const PersonalPageMain = (props) =>{
    //todo добавить проверку авторизирован ли пользователь
    const [code, setCode] = useState("");

    const [Pair1, setPair1] = useState(); //Решение объективно гавно, но пока так
    const [Pair2, setPair2] = useState();
    const [Pair3, setPair3] = useState();
    const [Pair4, setPair4] = useState();
    const [Pair5, setPair5] = useState();
    const [Pair6, setPair6] = useState();
    const [Pair7, setPair7] = useState();



    const submit = async (e) => {
        e.preventDefault();
        await fetch('api/Group/CreateGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                Code : code,
                timeTable: {
                    Pair1: Pair1,
                    Pair2: Pair2,
                    Pair3: Pair3,
                    Pair4: Pair4,
                    Pair5: Pair5,
                    Pair6: Pair6,
                    Pair7: Pair7,
                    
                }
                
            }) 
        })};
        return (
            < >
                <Header name={props.name} setName={props.setName}/>
                <div>
                    Hello {props.name}
                    <form onSubmit={submit}>
                        <h1 className="h3 mb-3 fw-normal">Введи расписание</h1>
                        <input  className="form-control" required
                                onChange={e => setCode(e.target.value)}
                        />
                        <input  className="form-control" required
                               onChange={e => setPair1(e.target.value)}
                        />

                        <input  className="form-control"  required
                                onChange={e => setPair2(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setPair3(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setPair4(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setPair5(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setPair6(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setPair7(e.target.value)}
                        />
                    
                        <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                    </form>

                </div>
            </>

        );
    }
