import Button from "../../button/Button.tsx";

export interface DisplayButtonProps {
    onEditClick?: () => void;
    onCloseClick: () => void;
}

function DisplayButtons(props: DisplayButtonProps) {
    const { onEditClick, onCloseClick } = props;

    return (
        <div className="display-buttons">
            <Button text='Close' outlined onClick={onCloseClick}/>
            {onEditClick && <Button text='Edit' onClick={onEditClick}/>}
        </div>
    );
}

export default DisplayButtons;