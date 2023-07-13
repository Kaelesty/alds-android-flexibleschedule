import Header from "../Header/header__navigation";
import React, {useState} from "react";

export const PersonalPageMain = (props) =>{
    //todo добавить проверку авторизирован ли пользователь
    const [code, setCode] = useState("");

    const [Day1, setDay1] = useState(); //Решение объективно гавно, но пока так
    const [Day2, setDay2] = useState();
    const [Day3, setDay3] = useState();
    const [Day4, setDay4] = useState();
    const [Day5, setDay5] = useState();
    const [Day6, setDay6] = useState();
    const [Day7, setDay7] = useState();



    const submit = async (e) => {
        e.preventDefault();
        await fetch('api/Group/CreateGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                Code : code,
                timeTable: {
                    Day1: Day1,
                    Day2: Day2,
                    Day3: Day3,
                    Day4: Day4,
                    Day5: Day5,
                    Day6: Day6,
                    Day7: Day7,
                    
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
                               onChange={e => setDay1(e.target.value)}
                        />

                        <input  className="form-control"  required
                                onChange={e => setDay2(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setDay3(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setDay4(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setDay5(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setDay6(e.target.value)}
                        />
                        <input  className="form-control"  required
                                onChange={e => setDay7(e.target.value)}
                        />
                    
                        <button className="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                    </form>

                </div>
            </>

        );
    }
