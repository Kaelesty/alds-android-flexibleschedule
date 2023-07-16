import Header from "../Header/header__navigation";
import React, {useState} from "react";
// ТУТ НАСРАНО АККУРАТНО!!!
export const PersonalPageMain = (props) =>{
    //todo добавить проверку авторизирован ли пользователь
    const [code, setCode] = useState('');
    const [Day1, setDay1] = useState(''); //Решение объективно гавно, но пока так
    const [Day2, setDay2] = useState('');
    const [Day3, setDay3] = useState('');
    const [Day4, setDay4] = useState('');
    const [Day5, setDay5] = useState('');
    const [Day6, setDay6] = useState('');
    const [Day7, setDay7] = useState('');
    const setDays = [setDay1,setDay2,setDay3,setDay4,setDay5,setDay6,setDay7]
    
    function serializeForm(formNode) {
        const { elements } = formNode
        let final = ''
        let i = 1
        Array.from(elements)
            .forEach((element) => {
                if(i%4!==0){
                    const {value} = element 
                    if(value!=='')
                        final +=value+ '&'
                    else{
                        final+='-&'
                    }
                    
                }
                else{
                    const {value} = element
                    if(value!=='')
                        final +=value+ '/'
                    else{
                        final+='-/'
                    }

                }
                i++;
            })
                console.log(final.slice(0,-1))
            return final.slice(0,-1)
    }
    function handleFormSubmit(event) {
        event.preventDefault()

        fetch('api/Group/CreateGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                Code : code,
                timeTable: {
                    Day1: serializeForm(table1),
                    Day2: serializeForm(table2),
                    Day3: serializeForm(table3),
                    Day4: serializeForm(table4),
                    Day5: serializeForm(table5),
                    Day6: serializeForm(table6),
                    Day7: serializeForm(table7),

                }

            }) 
        })
    }
    const table1 = document.getElementById('table1')
    const table2 = document.getElementById('table2')
    const table3 = document.getElementById('table3')
    const table4 = document.getElementById('table4')
    const table5 = document.getElementById('table5')
    const table6 = document.getElementById('table6')
    const table7 = document.getElementById('table7')

    const submit = async (e) => {
        serializeForm(table1,1)

        e.preventDefault();
};
        const PairFrom = <td>
            <tr>
                <input  className="form-control"  type="number" placeholder={'Номер пары'}/>
                <input  className="form-control" placeholder={'Предмет'}/>
                <input  className="form-control" placeholder={'Место'}/>
                <input  className="form-control" placeholder={'Преподаватель'}
                />
            </tr>
        </td> 
    
        const DayForm =<td>{PairFrom}{PairFrom}{PairFrom}{PairFrom}{PairFrom}{PairFrom}{PairFrom}</td>
        return (
            < >
                <Header name={props.name} setName={props.setName}/>
                <div>
                    Hello {props.name}
                    <form id="TimeTable" onSubmit={handleFormSubmit}>
                        <h2>Введите код группы</h2>
                        <input  className="form-control" required
                                onChange={e => setCode(e.target.value)}
                        />
                        <h1 className="h3 mb-3 fw-normal">Введите расписание</h1>
                        <h2>Понедельник</h2>
                        <form id = 'table1'>
                            {DayForm}
                        </form>
                        <h2>Вторник</h2>
                        <form id = 'table2'>
                            {DayForm}
                        </form>
                        <h2>Среда</h2>
                        <form id = 'table3'>
                            {DayForm}
                        </form>
                        <h2>Четверг</h2>
                        <form id = 'table4'>
                            {DayForm}
                        </form>
                        <h2>Пятница</h2>
                        <form id = 'table5'>
                            {DayForm}
                        </form>
                        <h2>Суббота</h2>
                        <form id = 'table6'>
                            {DayForm}
                        </form>
                        <h2>Воскресение</h2>
                        <form id = 'table7'>
                            {DayForm}
                        </form>
                    
                        <button className="w-100 btn btn-lg btn-primary" type="submit">Сохранить расписание</button>
                    </form>

                </div>
            </>

        );
    }
