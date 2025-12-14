import {X} from "lucide-react";

export interface ModalHeaderProps {
    title: string;
    subtitle?: string;
    onClose: () => void;
}

function ModalHeader(props: ModalHeaderProps) {
    const { title, subtitle, onClose } = props;

    return (
        <div className="modal-header">
            <div className="modal-header-text">
                <h2 className="modal-title">{title}</h2>
                {subtitle && (
                    <p className="modal-subtitle">{subtitle}</p>
                )}
            </div>

            <button className="modal-close" onClick={onClose}>
                <X width={16} height={16} />
            </button>
        </div>
    );
}

export default ModalHeader;