import Button from "../../common/button/Button.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";

export interface MenuVersionsComparisonButtons {
    onClose: () => void;
    selectedVersionId: string;
}

function MenuVersionsComparisonButtons(props: MenuVersionsComparisonButtons) {
    const {onClose, selectedVersionId} = props;

    const queryClient = useQueryClient();

    const handleRestore = useMutation({
        mutationFn: (id: string) => {
            return menuService.restoreMenuVersion(id);
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["menu-versions", "draft"]})
                .then(() => onClose());
        },
        onError: (error) => {
            console.error("Unexpected error:", error);
        },
    });

    return (
        <div className="menu-versions-comparison-buttons">
            <Button text="Close" outlined onClick={onClose}/>
            <Button text="Restore" onClick={() => handleRestore.mutate(selectedVersionId)}/>
        </div>
    );
}

export default MenuVersionsComparisonButtons;