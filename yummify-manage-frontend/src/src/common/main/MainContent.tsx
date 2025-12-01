import React from "react";

export interface MainContentProps {
    sidebarOpen: boolean;
    children: React.ReactNode;
}

function MainContent(props: MainContentProps) {
    const { sidebarOpen, children } = props;

    return (
        <div className={`main-content ${sidebarOpen ? 'sidebar-open' : ''}`}>
            {children}
        </div>
    );
}

export default MainContent;