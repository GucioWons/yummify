import Sidebar from "./sidebar/Sidebar.tsx";
import Navbar from "./navbar/Navbar.tsx";
import React, {useState} from "react";
import "./MainWrapper.css";
import MainContent from "./MainContent.tsx";

export interface MainWrapperProps {
    children: React.ReactNode;
}

function MainWrapper(props: MainWrapperProps) {
    const [sideBarOpen, setSideBarOpen] = useState(false);

    return (
        <>
            <Sidebar isOpen={sideBarOpen}/>
            <Navbar sidebarOpen={sideBarOpen} toggleSidebarOpen={() => setSideBarOpen(!sideBarOpen)}/>
            <MainContent>
                {props.children}
            </MainContent>
        </>
    )
}

export default MainWrapper;