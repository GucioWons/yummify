import PageTitle from "../common/PageTitle.tsx";
import Button from "../common/button/Button.tsx";
import {ClipboardClock} from "lucide-react";
import PublishedMenuDisplay from "./display/PublishedMenuDisplay.tsx";

function MenuPage() {
    return (
        <>
            <PageTitle
                title='Menu'
                description='View and organize your restaurant menu'
                button={
                    <Button
                        text='Versions'
                        icon={ClipboardClock}
                        onClick={() => {}}
                    />
                }
            />
            <PublishedMenuDisplay />
        </>
    );
}

export default MenuPage;