import React, {useState} from "react";
import {ChevronDown, ChevronUp} from "lucide-react";
import "./Expandable.css"

export interface ExpandableProps {
    title?: string;
    children: React.ReactNode;
}

function Expandable(props: ExpandableProps) {
    const { title, children } = props;

    const [open, setOpen] = useState(false);

    return (
        <div className="expandable">
            <button
                type="button"
                onClick={() => setOpen(!open)}
                className="expandable-button"
            >
                {open ? <ChevronUp size={12}/> : <ChevronDown size={12}/>} {title}
            </button>

            {open && children}
        </div>
    );
}

export default Expandable;