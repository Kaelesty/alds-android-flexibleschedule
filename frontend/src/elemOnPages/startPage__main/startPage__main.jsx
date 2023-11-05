import style from "./startPage__main.module.css";
import sokol from './../../images/sokol.svg';
import { useState } from "react";
import './animation.css'


const StartPage__main = () => {

    const [sokolAnimate, setSokol] = useState({
        animation: "animSokol 2s infinite ease-in-out"
    })

    const sokolAnim = () => {
        setSokol({animation: 'SokolPoletAnim 5s cubic-bezier(0.175, 0.885, 0.32, 1.275)'});
        setTimeout(() => setSokol({animation: "animSokol 2s infinite ease-in-out"}), 5000)
        //document.getElementById('sokol').style.animation = 'SokolPoletAnim 5s cubic-bezier(0.175, 0.885, 0.32, 1.275)'
    }

    return (
        <div className={style.startPage__main}>
            <h1 className={style.startPage__h1 + " " + "new_font"}>Расписание для своих</h1>
            <div className={style.StartPage__sokolConteiner}>
                <img src={sokol} id="sokol" className={style.StartPage__sokol} style={sokolAnimate} onClick={() => sokolAnim()}></img>
            </div>

        </div>
    );
}

export default StartPage__main;