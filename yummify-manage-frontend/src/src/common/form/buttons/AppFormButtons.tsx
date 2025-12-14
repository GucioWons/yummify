import AppFormCancelButton from "./AppFormCancelButton.tsx";
import AppFormSubmitButton from "./AppFormSubmitButton.tsx";

function AppFormButtons() {
    return (
        <div className="app-form-buttons">
            <AppFormCancelButton />
            <AppFormSubmitButton />
        </div>
    )
}

export default AppFormButtons;