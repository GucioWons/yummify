import Button from "../common/button/Button.tsx";
import {ClipboardClock, Save} from "lucide-react";

export interface MenuPageTitleButtonsProps {
    isDraft?: boolean;
}

function MenuPageTitleButtons(props: MenuPageTitleButtonsProps) {
    const {isDraft} = props;

    return (
        <div style={{
            display: "flex",
            gap: 8
        }}>
            <Button
                text='Versions'
                icon={ClipboardClock}
                onClick={() => {}}
            />
            {isDraft &&
                <Button
                    text='Publish'
                    icon={Save}
                    onClick={() => {}}
                />
            }
        </div>
    );
}

export default MenuPageTitleButtons;