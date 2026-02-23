import {Dtos} from "../dtos.ts";
import TranslatedStringDto = Dtos.TranslatedStringDto;
import FieldDisplay from "./FieldDisplay.tsx";

export interface TranslatedTextFieldDisplayProps {
    label: string;
    value?: TranslatedStringDto
}

function TranslatedTextFieldDisplay(props: TranslatedTextFieldDisplayProps) {
    const {label, value} = props;

    if (!value) return null;

    return (
        <FieldDisplay label={label}>
            {Object.entries(value.translations).map(([lang, text]) => (
                    <span key={lang}>
                        {lang}: {text}
                    </span>
                ))}
        </FieldDisplay>
    );
}

export default TranslatedTextFieldDisplay;