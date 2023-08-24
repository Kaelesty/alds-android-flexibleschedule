import Lesson from "./lesson/lesson";
import style from "./day.module.css";

const Day = (props) => {
    const mas = [6,0,1,2,3,4,5]
    let day = mas[new Date().getDay()];
    return (
        <div className={style.day}>
            {props.days[day].pairs.map(elem => 
                <Lesson para={elem}/>
            )}
        </div>
    )
}

export default Day;