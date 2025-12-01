import Sidebar from "./sidebar/Sidebar.tsx";
import Navbar from "./navbar/Navbar.tsx";
import React, {useState} from "react";
import "./MainWrapper.css";
import MainContent from "./MainContent.tsx";

export interface MainWrapperProps {
    children: React.ReactNode;
}

function MainWrapper(props: MainWrapperProps) {
    const [sidebarOpen, setSidebarOpen] = useState(false);

    return (
        <>
            <Sidebar isOpen={sidebarOpen}/>
            <Navbar sidebarOpen={sidebarOpen} toggleSidebarOpen={() => setSidebarOpen(!sidebarOpen)}/>
            <MainContent sidebarOpen={sidebarOpen}>
                {props.children}
            </MainContent>
        </>
    )
}

export default MainWrapper;