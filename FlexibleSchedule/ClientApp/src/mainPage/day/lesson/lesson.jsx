
import style from "./lesson.module.css";
const Lesson = (props) => {
    return (
        <div>
            <p className={style.lesson__p}>{props.para.time}</p>
            <p className={style.lesson__p}>{props.para.info}</p>
            <p className={style.lesson__p}>{props.para.place}</p>
            <p className={style.lesson__p}>{props.para.teacher}</p>
        </div>
    )
}

export default Lesson;