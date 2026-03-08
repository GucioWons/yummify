import PageTitle from "../common/PageTitle.tsx";
import PublishedMenuDisplay from "./published/PublishedMenuDisplay.tsx";
import {useState} from "react";
import DraftMenuDisplay from "./draft/DraftMenuDisplay.tsx";
import "./MenuPage.css"
import MenuPageTitleButtons from "./MenuPageTitleButtons.tsx";
import MenuPageButtons from "./MenuPageButtons.tsx";

function MenuPage() {
    const [selectedMenu, setSelectedMenu] = useState<"published" | "draft">("published")

    const isDraft = selectedMenu === "draft"
    const isPublished = selectedMenu === "published"

    return (
        <>
            <PageTitle
                title='Menu'
                description='View and organize your restaurant menu'
                button={<MenuPageTitleButtons isDraft={isDraft}/>}
            />

            <MenuPageButtons
                isDraft={isDraft}
                isPublished={isPublished}
                onDraftClick={() => setSelectedMenu("draft")}
                onPublishedClick={() => setSelectedMenu("published")}
            />

            {isPublished && <PublishedMenuDisplay />}
            {isDraft && <DraftMenuDisplay />}
        </>
    );
}

export default MenuPage;