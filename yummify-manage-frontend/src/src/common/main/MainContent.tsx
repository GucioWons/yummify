import React from "react";

export interface MainContentProps {
    children: React.ReactNode;
}

function MainContent(props: MainContentProps) {
    return (
        <div className="main-content">
            {props.children}
        </div>
    );
}

export default MainContent;