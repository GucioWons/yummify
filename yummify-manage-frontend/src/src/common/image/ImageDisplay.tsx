import "./ImageDisplay.css"

export interface ImageDisplayProps {
    image: File;
}

function ImageDisplay(props: ImageDisplayProps) {
    const {image} = props;

    return <img className='image-display' src={URL.createObjectURL(image)} alt=''/>
}

export default ImageDisplay;