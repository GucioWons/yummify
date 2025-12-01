import "./PageTitle.css";
import {JSX} from "react";

export interface PageTitleProps {
    title: string;
    description: string;
    button?: JSX.Element;
}

function PageTitle(props: PageTitleProps) {
    const { title, description, button } = props;

    return (
        <div className='page-title-container'>
            <div className='page-title'>
                <h2>{title}</h2>
                <h3>{description}</h3>
            </div>
            {button}
        </div>
    )
}

export default PageTitle;