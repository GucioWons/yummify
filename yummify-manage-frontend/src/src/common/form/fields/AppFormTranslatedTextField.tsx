import {FieldPath, FieldValues, useFormContext} from "react-hook-form";
import {DTOs} from "../../dtos.ts";
import Language = DTOs.Language;
import TextInput from "../../input/TextInput.tsx";
import Expandable from "../../expandable/Expandable.tsx";

export interface AppFormTranslatedTextFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
    label: string;
    requiredLanguages: Language[];
    optionalLanguages: Language[];
    placeholder?: string;
}

function AppFormTranslatedTextField<T extends FieldValues>(props: AppFormTranslatedTextFieldProps<T>) {
    const { name, label, requiredLanguages, optionalLanguages, placeholder } = props;

    const { register } = useFormContext<T>();

    return (
        <div>
            {requiredLanguages.map((lang) => (
                <TextInput
                    key={lang}
                    label={`${label} (${lang})`}
                    placeholder={placeholder}
                    {...register(`${name}.translations.${lang}` as FieldPath<T>)}
                />
            ))}

            {optionalLanguages.length > 0 && (
                <Expandable title={`Optional languages (${optionalLanguages.length})`}>
                    {optionalLanguages.map((lang) => (
                        <TextInput
                            key={lang}
                            label={`${label} (${lang})`}
                            placeholder={placeholder}
                            {...register(`${name}.translations.${lang}` as FieldPath<T>)}
                        />
                    ))}
                </Expandable>
            )}
        </div>
    );
}

export default AppFormTranslatedTextField;