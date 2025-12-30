import {useCallback, useState} from "react";
import {useDropzone} from "react-dropzone";
import "./ImagePicker.css"

interface ImagePickerProps {
    value?: File;
    onChange: (file?: File) => void;
}

function ImagePicker(props: ImagePickerProps) {
    const {value, onChange} = props;

    const [preview, setPreview] = useState<string | null>(
        value ? URL.createObjectURL(value) : null
    );

    const onDrop = useCallback(
        (acceptedFiles: File[]) => {
            if (acceptedFiles.length === 0) return;
            const file = acceptedFiles[0];
            setPreview(URL.createObjectURL(file));
            onChange(file);
        },
        [onChange]
    );

    const {getRootProps, getInputProps, isDragActive} = useDropzone({
        onDrop,
        accept: {"image/*": []},
        multiple: false,
    });

    return (
        <div {...getRootProps()} className="image-picker">
            <input {...getInputProps()} />
            {preview ? (
                <img src={preview} alt="Preview" className="image-preview" />
            ) : isDragActive ? (
                <p>Drop image here …</p>
            ) : (
                <p>Drag & drop image, or click to select</p>
            )}
        </div>
    );
}

export default ImagePicker;