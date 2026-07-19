interface CheckboxProps {
    label: string;
    checked: boolean;
    onChange: () => void;
}

function Checkbox(props: CheckboxProps) {
    const { label, checked, onChange } = props;

    return (
        <label style={{ display: "flex", gap: 8, alignItems: "center" }}>
            <input
                type="checkbox"
                checked={checked}
                onChange={onChange}
            />

            <span>{label}</span>
        </label>
    );
}

export default Checkbox;