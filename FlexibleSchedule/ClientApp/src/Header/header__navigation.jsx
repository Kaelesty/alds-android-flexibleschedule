import './header__navigation.css';
import {Link, NavLink} from "react-router-dom";

const Header = (props) => {
    const logout = async () => {
        await fetch('api/Auth/Logout', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include',
        });

        props.setUser({name: null, isAuthorized: false});
        window.location.reload()
    }
    let menu;
    console.log(props.user.isAuthorized)
    if(props.user.isAuthorized === null){
        menu = (
            <p>Loading...</p>
        );

    }
    if (props.user?.isAuthorized) { // не забыть поменять
        menu = (
            <nav className="header__nav">
                <Link to="/" className="header__a"  >Главная страница </Link>
                <Link to="/MyTimeTables" className="header__a" >Мои Расписания</Link>
                <Link to="/PersonalPageMain" className="header__a"  >{props.user.name}</Link>
                <Link to="/" className="header__a" onClick={logout} >Выйти </Link>

            </nav>
        )
    } 
    if(props.user?.isAuthorized===false){
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