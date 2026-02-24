import {useState} from "react";
import {useQuery} from "@tanstack/react-query";
import Modal from "../../common/modal/Modal.tsx";
import {Dtos} from "../../common/dtos.ts";
import {ingredientService} from "../service/ingredientService.ts";
import IngredientDisplay from "./IngredientDisplay.tsx";
import IngredientManageDto = Dtos.IngredientManageDto;
import IngredientUpdateForm from "./IngredientUpdateForm.tsx";

export interface IngredientDisplayModalProps {
    id: string
    onClose: () => void;
}

function IngredientDisplayModal(props: IngredientDisplayModalProps) {
    const {id, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const {data, isLoading, isError} = useQuery<IngredientManageDto>({
        queryKey: ["ingredients", id],
        queryFn: () => ingredientService.getIngredient(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składniku.</div>;

    return (
        <Modal title="Ingredient Details" onClose={onClose}>
            {isInEditState
                ? <IngredientUpdateForm ingredient={data!} onCancel={() => setIsInEditState(false)}/>
                : <IngredientDisplay
                    ingredient={data!}
                    onEditClick={() => setIsInEditState(true)}
                    onCloseClick={onClose}
                />
            }
        </Modal>
    )
}

export default IngredientDisplayModal;