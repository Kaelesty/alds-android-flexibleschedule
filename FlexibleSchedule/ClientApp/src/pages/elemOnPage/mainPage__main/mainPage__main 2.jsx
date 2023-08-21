import Day from "./day/day";
import style from './mainPage__main.module.css'

const MainPage__main = (props) => {
    let cout = 1;
    return (
        <main className={style.main}>
        {props.mas.days.map(elem => 
            <Day para={elem} id={cout++}/>    
        )}
        </main>
    );
}

export default MainPage__main;