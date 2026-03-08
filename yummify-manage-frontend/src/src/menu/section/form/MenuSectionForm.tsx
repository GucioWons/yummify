import {Dtos} from "../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import AppFormTranslatedTextField from "../../../common/form/fields/AppFormTranslatedTextField.tsx";
import AppForm from "../../../common/form/AppForm.tsx";
import Language = Dtos.Language;
import UpdateMenuSectionNameRequest = Dtos.UpdateMenuSectionNameRequest;

export interface MenuSectionFormProps {
    section?: MenuSectionManageDto;
    onCancel: () => void;
    handleSubmit: (data: UpdateMenuSectionNameRequest) => void;
}

function MenuSectionForm(props: MenuSectionFormProps) {
    const {section, onCancel, handleSubmit} = props;

    return (
        <AppForm
            <UpdateMenuSectionNameRequest>
            initialData={section ? {name: section.name} : {}}
            onSubmit={handleSubmit}
            onCancel={onCancel}
        >
            <AppFormTranslatedTextField
                name="name"
                label="Name"
                placeholder="Eg. Drinks"
                requiredLanguages={[Language.EN]}
                optionalLanguages={[Language.PL, Language.DE]}
            />
        </AppForm>
    )
}

export default MenuSectionForm;