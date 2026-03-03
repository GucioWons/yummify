import PageTitle from "../common/PageTitle.tsx";
import Button from "../common/button/Button.tsx";
import {ClipboardClock} from "lucide-react";
import PublishedMenuDisplay from "./display/published/PublishedMenuDisplay.tsx";
import {useState} from "react";
import DraftMenuDisplay from "./display/draft/DraftMenuDisplay.tsx";

function MenuPage() {
    const [selectedMenu, setSelectedMenu] = useState<"published" | "draft">("published")

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
            <div style={{display: 'flex', gap: 8, paddingBottom: 16}}>
                <Button text="Published Menu" outlined={selectedMenu === "published"} onClick={() => setSelectedMenu("published")} />
                <Button text="Draft Menu" outlined={selectedMenu === "draft"} onClick={() => setSelectedMenu("draft")} />
            </div>

            {selectedMenu === "published" && <PublishedMenuDisplay />}
            {selectedMenu === "draft" && <DraftMenuDisplay />}
        </>
    );
}

export default MenuPage;