import Button from "../common/button/Button.tsx";

export interface MenuPageButtonsProps {
    isDraft: boolean;
    isPublished: boolean;
    onDraftClick: () => void;
    onPublishedClick: () => void;
}

function MenuPageButtons(props: MenuPageButtonsProps) {
    const {isDraft, isPublished, onDraftClick, onPublishedClick} = props;

    return (
        <div style={{display: 'flex', gap: 8, paddingBottom: 16}}>
            <Button text="Published Menu" outlined={isPublished} onClick={onPublishedClick}/>
            <Button text="Draft Menu" outlined={isDraft} onClick={onDraftClick}/>
        </div>
    )
}

export default MenuPageButtons;