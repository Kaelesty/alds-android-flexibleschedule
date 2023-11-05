import Header__navigation from "../../elemOnPages/header__navigation/header__navigation"
import StartPage__main from "../../elemOnPages/startPage__main/startPage__main";
import "./startPage"

const StartPages = (props) => {
    return (
        <div className="startPage">
            <Header__navigation/>
            <StartPage__main />
        </div>
    )
}

export default StartPages;