import {Dtos} from "../../../common/dtos.ts";
import Modal from "../../../common/modal/Modal.tsx";
import TableOtpDto = Dtos.TableOtpDto;
import TableDto = Dtos.TableDto;
import QrCodeDisplay from "./QrCodeDisplay.tsx";

export interface QrCodeDisplayModalProps {
    qrCode: TableOtpDto;
    table: TableDto;
    onClose: () => void;
}

function QrCodeDisplayModal(props: QrCodeDisplayModalProps) {
    const {qrCode, table, onClose} = props;

    return (
        <Modal
            title={`QR Code - ${table.name}`}
            onClose={onClose}
        >
            <QrCodeDisplay qrCode={qrCode} />
        </Modal>
    );
}

export default QrCodeDisplayModal;