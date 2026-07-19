import {Dtos} from "../../../common/dtos.ts";
import TableOtpDto = Dtos.TableOtpDto;
import {QRCodeSVG} from "qrcode.react";
import "./QrCodeDisplay.css"

export interface QrCodeDisplayProps {
    qrCode: TableOtpDto;
}

function QrCodeDisplay(props: QrCodeDisplayProps) {
    const {qrCode} = props;

    const qrCodeValue = JSON.stringify(qrCode);

    return (
        <div className="qr-code-display">
            <div className="qr-code-wrapper">
                <QRCodeSVG
                    value={qrCodeValue}
                    width={300}
                    height={300}
                    fgColor="#059669"
                />
            </div>
            <div>
                Id: ${qrCode.tableId}
            </div>
            <div>
                Code: ${qrCode.otp}
            </div>
        </div>
    );
}

export default QrCodeDisplay;