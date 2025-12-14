import {ReactNode} from "react";
import "./Modal.css";

export interface ModalProps {
    title: string;
    onClose: () => void;
    children: ReactNode;
}

function Modal(props: ModalProps) {
    const {title, onClose, children} = props;

    return (
        <div className="modal-backdrop" onClick={onClose}>
            <div
                className="modal-content"
                onClick={(e) => e.stopPropagation()}
            >
                {title}
                {children}
            </div>
        </div>
    )
}

export default Modal;