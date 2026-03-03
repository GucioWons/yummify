import {useCallback, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import Modal from "../../common/modal/Modal.tsx";
import {Dtos} from "../../common/dtos.ts";
import {ingredientService} from "../service/ingredientService.ts";
import IngredientDisplay from "./IngredientDisplay.tsx";
import IngredientManageDto = Dtos.IngredientManageDto;
import IngredientUpdateForm from "./IngredientUpdateForm.tsx";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";

export interface IngredientDisplayModalProps {
    id: string
    onClose: () => void;
}

function IngredientDisplayModal(props: IngredientDisplayModalProps) {
    const {id, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const getTitle = useCallback(() => {
        return isInEditState ? "Edit Ingredient" : "Ingredient Details";
    }, [isInEditState]);

    const {data: ingredient, isLoading, isError} = useQuery<IngredientManageDto>({
        queryKey: ["ingredients", id],
        queryFn: () => ingredientService.getIngredient(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania składniku.</div>;

    return (
        <Modal title={getTitle()} onClose={onClose}>
            {isInEditState
                ? <IngredientUpdateForm ingredient={ingredient!} onCancel={() => setIsInEditState(false)}/>
                : <IngredientDisplay
                    ingredient={ingredient!}
                    onEditClick={() => setIsInEditState(true)}
                    onCloseClick={onClose}
                />
            }
        </Modal>
    )
}

export default IngredientDisplayModal;