import Input, {InputProps} from "./Input.tsx";

function TextInput(props: InputProps) {
    return <Input type="text" {...props} />;
}

export default TextInput;