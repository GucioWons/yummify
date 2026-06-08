import {ReactNode} from "react";
import "./Modal.css";
import ModalHeader from "./ModalHeader.tsx";
import Divider from "../divider/Divider.tsx";

export interface ModalProps {
    title: string;
    subtitle?: string;
    onClose: () => void;
    children: ReactNode;
    fullWidth?: boolean;
    fullHeight?: boolean;
}

function Modal(props: ModalProps) {
    const {title, subtitle, onClose, children, fullWidth, fullHeight} = props;

    return (
        <div className="modal-backdrop" onClick={onClose}>
            <div
                className={`modal-content ${fullWidth && "full-width"} ${fullHeight && "full-height"}`}
                onClick={(e) => e.stopPropagation()}
            >
                <ModalHeader title={title} subtitle={subtitle} onClose={onClose}/>

                <Divider />

                <div className="modal-body">
                    {children}
                </div>
            </div>
        </div>
    )
}

export default Modal;