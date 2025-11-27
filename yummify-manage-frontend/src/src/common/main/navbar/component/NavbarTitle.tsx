export interface NavbarTitleProps {
    restaurantName?: string,
}

function NavbarTitle(props: NavbarTitleProps) {
    const {restaurantName} = props;

    return (
        <div className="navbar-title">
            <h1>{restaurantName}</h1>
        </div>
    );
}

export default NavbarTitle;