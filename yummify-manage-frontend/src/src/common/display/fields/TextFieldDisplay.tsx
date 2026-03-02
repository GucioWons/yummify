import FieldDisplay from "./FieldDisplay.tsx";

export interface TextFieldDisplayProps {
    label: string;
    text: string;
}

function TextFieldDisplay(props: TextFieldDisplayProps) {
    const {label, text} = props;

    return (
        <FieldDisplay label={label}>
            {text}
        </FieldDisplay>
    );
}

export default TextFieldDisplay;