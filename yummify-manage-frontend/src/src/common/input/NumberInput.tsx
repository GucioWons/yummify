import Input, {InputProps} from "./Input.tsx";

function NumberInput(props: InputProps) {
    return <Input type="text" {...props} />;
}

export default NumberInput;