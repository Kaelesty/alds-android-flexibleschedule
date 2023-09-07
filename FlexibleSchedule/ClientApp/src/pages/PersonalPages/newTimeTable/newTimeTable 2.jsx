import Header from "../../../Header/header__navigation";

const NewTimeTable = (props) => {
    return (
        <>
            <Header name={props.name} setName={props.setName} />
        </>
    );
}

export default NewTimeTable;