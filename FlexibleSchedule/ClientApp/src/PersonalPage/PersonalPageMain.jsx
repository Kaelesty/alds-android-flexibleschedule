import Header from "../Header/header__navigation";
import React, {useEffect, useState} from "react";
import {useForm} from "react-hook-form";
import {Container} from "reactstrap";

export const PersonalPageMain = (props) => {
    const [maxSize,setMaxSize] = useState()
    window.onload = function() { // можно также использовать window.addEventListener('load', (event) => {
        setMaxSize(3)
    };  
    //todo добавить проверку авторизирован ли пользователь
    const TimeTable = {days:[]}
    const MainForm = document.getElementById('form')
    const onSubmit = (event) =>{
        setMaxSize(maxSize)
        event.preventDefault()
        const subForms = MainForm.querySelectorAll('[id=subform]')
        console.log(subForms.length)
        for(let i =0;i<subForms.length;i++){
            const Day = {pairs:[]}
            for(let j =0;j<subForms[i].length;j++){
                const Time = subForms[j].querySelector('[name="Time'+i+j+'"]')
                const Info = subForms[j].querySelector('[name="Info'+i+j+'"]')
                const Place = subForms[j].querySelector('[name="Place'+i+j+'"]')
                const Teacher = subForms[j].querySelector('[name="Teacher'+i+j+'"]')
                const Pair = {
                    Time: Time.value,
                    Info: Info.value,
                    Place: Place.value,
                    Teacher: Teacher.value
                }

                Day.pairs.push(Pair)
            }
            TimeTable.days.push(Day)
        }
        const code = MainForm.querySelector('[name="Code"]').value
        console.log(JSON.stringify({
            code,
            TimeTable
        }))
        const promise = fetch('api/Group/CreateGroup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
            body: JSON.stringify({
                code,
                TimeTable
            })
        })
        promise.then(res => {
            if(res.status===201){
                alert("Расписание добавлено")
            }
            else{
                alert("Ошибка! Расписание не добавлено, такой код уже существует")
            }
        })
        }


    const GetPair = (NumberDay,NumberPair) =>{
        return <div >
            <input className={'Pair'} placeholder={'Время'} type={"text"} name={'Time' + NumberDay+NumberPair}></input>
            <input className={'Pair'} placeholder={'Информация'} type={"text"} name={'Info' + NumberDay+NumberPair}></input>
            <input className={'Pair'} placeholder={'Место'} type={"text"} name={'Place' + NumberDay+NumberPair}></input>
            <input  className={'Pair'} placeholder={'Преподаватель'} type={"text"} name={'Teacher' + NumberDay+NumberPair}></input>
        </div>;
        
    }
    const GetDay = (Number) =>{
        console.log("getDay")
        return <>
            {numbers.slice(7-maxSize).map((number) =>
                <>
                <form id={'subsubform'}>
                    {GetPair(number,Number)}
                </form>
                </>
            )}
        </>;

    }
    const numbers = [0,1,2,3,4,5,6]
    const DayOfWeek = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресение']
    const onChangeMax = (e) =>{
        if(e.target.value >=0 && e.target.value <8)
        setMaxSize(e.target.value)
    }
    return (
        < >
            <Header name={props.name} setName={props.setName}/>
            <div>
                Максимальное кол-во пар за день -  
            <input className={"MaxSize"} onChange={onChangeMax} required={true} type={"number"} name={'MaxSize'} value={maxSize}></input>
            </div>
            <form id={'form'} onSubmit={onSubmit}>

                <button type={"submit"}>Создать расписание</button>
                <input placeholder={"Код группы"} required={true} type={"text"} name={'Code'}></input>
                {numbers.map((number) => 
                    <>
                        <h3>{DayOfWeek[number]}</h3>
                    <form id={'subform'}>
                        {GetDay(number)}
                    </form>
                    </>
                )}
            </form>
        </>

    );
}

