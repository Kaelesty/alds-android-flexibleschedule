import './day.css';
import Lesson from './lesson/lesson';

const week = [
    'Вс',
    'Пн',
    'Вт',
    'Ср',
    'Чт',
    'Пт',
    'Сб'
]

const Day = (props) => {
    return (
        <div className={"day id" + props.id} id={props.id}>
            <div style={{fontWeight:"bold", textAlign:"center"}}>{week[props.id%7]}</div>
            <div className='day__container'>
                {props.para.pairs.map(elem =>
                    <Lesson pair={elem}/>
                )}
            </div>
        </div>
    );
}

export default Day;