import Button from "../common/button/Button.tsx";
import {ClipboardClock, Save} from "lucide-react";
import {menuService} from "./service/menuService.ts";
import {useMutation, useQueryClient} from "@tanstack/react-query";

export interface MenuPageTitleButtonsProps {
    isDraft?: boolean;
}

function MenuPageTitleButtons(props: MenuPageTitleButtonsProps) {
    const {isDraft} = props;

    const queryClient = useQueryClient();

    const handlePublish = useMutation({
        mutationFn: () => menuService.publishMenuVersion(),
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["menu-versions", "draft"]}),
        onError: (error) => console.error("Unexpected error:", error),
    });

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
                    onClick={handlePublish.mutate}
                />
            }
        </div>
    );
}

export default MenuPageTitleButtons;