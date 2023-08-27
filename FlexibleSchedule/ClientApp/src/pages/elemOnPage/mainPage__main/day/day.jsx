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
    const sortPairs = (a,b) =>{
        if(a.time>b.time){
            return 1
        }
        else {
            return -1
        }
    }

    return (
        <div className={"day id" + props.id} id={props.id}>
            <div style={{fontWeight:"bold", textAlign:"center"}}>{week[props.id%7]}</div>
            <div className='day__container'>
                {props.para.pairs.sort(sortPairs).map(elem =>
                    <Lesson pair={elem}/>
                )}
            </div>
        </div>
    );
}

export default Day;