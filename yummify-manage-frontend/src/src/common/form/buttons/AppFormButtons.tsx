import AppFormCancelButton from "./AppFormCancelButton.tsx";
import AppFormSubmitButton from "./AppFormSubmitButton.tsx";

export interface AppFormButtonsProps {
    onCancel: () => void;
}

function AppFormButtons(props: AppFormButtonsProps) {
    const {onCancel} = props;

    return (
        <div className="app-form-buttons">
            <AppFormCancelButton onCancel={onCancel} />
            <AppFormSubmitButton />
        </div>
    )
}

export default AppFormButtons;