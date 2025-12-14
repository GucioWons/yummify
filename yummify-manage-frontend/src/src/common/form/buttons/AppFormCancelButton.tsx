import Button from "../../button/Button.tsx";

export interface AppFormCancelButtonProps {
    onCancel: () => void;
}

function AppFormCancelButton(props: AppFormCancelButtonProps) {
    const { onCancel } = props;

    return <Button text="Cancel" onClick={onCancel} type="button" outlined/>
}

export default AppFormCancelButton;