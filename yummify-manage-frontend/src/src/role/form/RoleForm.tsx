import {Dtos} from "../../common/dtos.ts";
import RoleManageDto = Dtos.RoleManageDto;
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import Language = Dtos.Language;
import AppFormPermissionsField from "./permissions/AppFormPermissionsField.tsx";

export interface RoleFormProps {
    role?: RoleManageDto;
    onCancel: () => void;
    handleSubmit: (data: RoleManageDto) => void;
}

function RoleForm(props: RoleFormProps) {
    const {role, onCancel, handleSubmit} = props;

    return (
        <AppForm
            <RoleManageDto>
            initialData={role ?? {}}
            onSubmit={(data) => handleSubmit(data)}
            onCancel={onCancel}
        >
            <AppFormTranslatedTextField
                name="name"
                label="Name"
                placeholder="e.g. Bartender"
                requiredLanguages={[Language.EN]}
                optionalLanguages={[Language.PL, Language.DE]}
            />
            <AppFormPermissionsField
                name="permissions"
                label="Permissions"
            />
        </AppForm>
    );
}

export default RoleForm;