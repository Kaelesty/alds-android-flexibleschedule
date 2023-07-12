import './header__navigation.css';
import {Link, NavLink} from "react-router-dom";

const Header = (props) => {
    const logout = async () => {
        await fetch('api/Auth/Logout', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
        });

        props.setName(null);
    }
    let menu;
    console.log(props.name)
    if (props.name !== null) { // не забыть поменять
        menu = (
            <nav className="header__nav">
                <Link to="/" className="header__a" onClick={logout} >Выйти </Link>
                <Link to="/account" className="nav-link" >{props.name}</Link>
                <Link to="/MyTimeTables" className="nav-link" >Мои Расписания</Link>

            </nav>
        )
    } if(props.name === null){
        menu = (
            <nav className="header__nav">
                <NavLink className="header__a" to="/login">Войти</NavLink>
                <NavLink className="header__a" to="/register">Зарегистрироваться</NavLink>
            </nav>
        );

    }
    
    return (
        menu
    );
}

export default Header;