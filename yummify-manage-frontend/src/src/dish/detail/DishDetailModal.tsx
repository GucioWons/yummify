import Modal from "../../common/modal/Modal.tsx";
import DishDetail from "./DishDetail.tsx";
import {useState} from "react";
import DishForm from "../form/DishForm.tsx";
import {useQuery} from "@tanstack/react-query";
import {dishService} from "../service/dishService.ts";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;

export interface DishDetailModalProps {
    id: string
    onClose: () => void;
}

function DishDetailModal(props: DishDetailModalProps) {
    const {id, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const {data, isLoading, isError} = useQuery<DishManageDto>({
        queryKey: ["dishes", id],
        queryFn: () => dishService.getDish(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania dania.</div>;

    return (
        <Modal title="Dish Details" onClose={onClose}>
            {isInEditState
                ? <DishForm dish={data} onCancel={() => setIsInEditState(false)} />
                : <DishDetail dish={data!} onEditClick={() => setIsInEditState(true)} />
            }
        </Modal>
    )
}

export default DishDetailModal;