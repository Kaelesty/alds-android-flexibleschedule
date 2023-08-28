import { useLocation } from "react-router-dom";
import Header from "../../Header/header__navigation";
import StartPage__main from "../elemOnPage/startPage__main/startPage__main";
import style from "./startPage.module.css";

const StartPage = (props) => {
    const loc = useLocation()
    return (
        <div className={style.startPage}>
            <Header user={props.user} setUser={props.setUser}/>
            <StartPage__main />
            {console.log("loc", loc)}
        </div>
    )
}
export default StartPage;