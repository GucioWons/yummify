import Button from "../../button/Button.tsx";
import {ReactNode} from "react";
import {SquarePen} from "lucide-react";

export interface DisplayButtonProps {
    onEditClick?: () => void;
    onCloseClick: () => void;
    additionalButtons?: ReactNode;
}

function DisplayButtons(props: DisplayButtonProps) {
    const { onEditClick, onCloseClick, additionalButtons } = props;

    return (
        <div className="display-buttons">
            <Button text='Close' outlined onClick={onCloseClick}/>
            {additionalButtons}
            {onEditClick && <Button text='Edit' icon={SquarePen} onClick={onEditClick}/>}
        </div>
    );
}

export default DisplayButtons;