import './lesson.css';

const Lesson = (props) => {
    return (
        <div className="lesson">
            <div className="lesson__colomn1">
                <div className='lesson__time'>{props.pair.time}</div>
                <div className='lesson__place'>{props.pair.place}</div>
            </div>
            <div className="lesson__colomn2">
                <div className='lesson__info'>{props.pair.info}</div>
                <div className='lesson__teacher'>{props.pair.teacher}</div>
            </div>
        </div>
    );
}

export default Lesson;