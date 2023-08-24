import Header from "../Header/header__navigation";
import React, {useEffect, useState} from "react";
import {useForm} from "react-hook-form";
import {Container} from "reactstrap";
import {redirect, useNavigate} from "react-router-dom";

export const PersonalPageMain = (props) => {
    const navigate = useNavigate();

    const [maxSize,setMaxSize] = useState()
    useEffect(()=>{
        if(props.user.isAuthorized === false){
            navigate("/login")
        }
        setMaxSize(3)
    },[])
    //todo добавить проверку авторизирован ли пользователь
    const MainForm = document.getElementById('form')
    const onSubmit = (event) =>{
        const TimeTable = {days:[]}
        setMaxSize(maxSize)
        event.preventDefault()
        const subForms = MainForm.querySelectorAll('[id=subform]')
        for(let i =0;i<subForms.length;i++){
            const Day = {pairs:[]}
            for(let j =0;j<subForms[i].childNodes.length;j++) {
                const subsubForm = subForms[i].childNodes[j]
                const Time = subsubForm[0]
                const Info = subsubForm[1]
                const Place = subsubForm[2]
                const Teacher = subsubForm[3]
                const Pair = {
                    Time: Time.value,
                    Info: Info.value,
                    Place: Place.value,
                    Teacher: Teacher.value
                }
                if(!((Pair.Time=="" && Pair.Info=="" && Pair.Place=="" && Pair.Teacher=="") || (Pair.Time!="" && Pair.Info!="" && Pair.Place!="" && Pair.Teacher!=""))){
                    alert("у предмета должны быть заполнены все поля, либо не заполнено ни одно")
                    return
                }
                if(!(Pair.Time==="" && Pair.Info==="" && Pair.Place==="" && Pair.Teacher==="")){
                    Day.pairs.push(Pair)
                }
            }
                TimeTable.days.push(Day)
            
        }
        const code = MainForm.querySelector('[name="Code"]').value
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
            if(res.status===200){
                alert("Расписание добавлено")
            }
            if(res.status===400){
                alert("пользователь не может создать больше двух расписаний")

            }
            if(res.status===401){
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
        return <>
            {numbers.slice(7-maxSize).map((number) =>
                <>
                <form id={'subsubform'+number}>
                    {GetPair(number,Number)}
                </form>
                </>
            )}
        </>;

    }
    const numbers = [0,1,2,3,4,5,6]
    const DayOfWeek = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресение']
    const onChangeMax = (e) =>{
        if(e.target.value >0 && e.target.value <8)
        setMaxSize(e.target.value)
    }
    return (
        < >
            <Header user={props.user} setUser={props.setUser}/>
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

