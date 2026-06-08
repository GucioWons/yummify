import Modal from "../../common/modal/Modal.tsx";
import MenuVersionsComparison from "./MenuVersionsComparison.tsx";

export interface MenuVersionsModalProps {
    onClose: () => void;
}

function MenuVersionsModal(props: MenuVersionsModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Menu versions" onClose={onClose} fullWidth fullHeight>
            <MenuVersionsComparison />
        </Modal>
    );
}

export default MenuVersionsModal;