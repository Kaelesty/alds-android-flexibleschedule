import './header__navigation.css';
import { NavLink } from "react-router-dom";

const Header__navigation = (props) => {
    return (
        <nav className="header__nav">
            <NavLink className="header__a" to="/login">Войти</NavLink>
            <NavLink className="header__a" to="/registration">Зарегистрироваться</NavLink>
        </nav>
    )
}

export default Header__navigation;