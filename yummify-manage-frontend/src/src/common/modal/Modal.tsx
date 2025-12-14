import {ReactNode} from "react";
import "./Modal.css";
import ModalHeader from "./ModalHeader.tsx";
import Divider from "../divider/Divider.tsx";

export interface ModalProps {
    title: string;
    subtitle?: string;
    onClose: () => void;
    children: ReactNode;
}

function Modal(props: ModalProps) {
    const {title, subtitle, onClose, children} = props;

    return (
        <div className="modal-backdrop" onClick={onClose}>
            <div
                className="modal-content"
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