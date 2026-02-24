import Modal from "../../common/modal/Modal.tsx";
import DishDetail from "./DishDetail.tsx";
import {useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {dishService} from "../service/dishService.ts";
import {Dtos} from "../../common/dtos.ts";
import DishUpdateForm from "../form/DishUpdateForm.tsx";
import DishManageDto = Dtos.DishManageDto;
import {imageService} from "../../common/image/imageService.ts";

export interface DishDetailModalProps {
    id: string
    onClose: () => void;
}

function DishDetailModal(props: DishDetailModalProps) {
    const {id, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const {data: dish, isLoading: isDishLoading, isError: isDishError} = useQuery<DishManageDto>({
        queryKey: ["dishes", id],
        queryFn: () => dishService.getDish(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const {data: image, isLoading: isImageLoading, isError: isImageError} = useQuery<File>({
        queryKey: ["dish-image", id],
        queryFn: () => imageService.fetchImageFile(dish!.imageUrl),
        staleTime: 1000 * 60 * 5,
    });

    if (isDishLoading || isImageLoading) return <div>Ładowanie...</div>;
    if (isDishError || isImageError) return <div>Błąd podczas pobierania dania.</div>;

    return (
        <Modal title="Dish Details" onClose={onClose}>
            {isInEditState
                ? <DishUpdateForm dish={dish!} image={image} onCancel={() => setIsInEditState(false)} />
                : <DishDetail dish={dish!} image={image} onEditClick={() => setIsInEditState(true)} />
            }
        </Modal>
    )
}

export default DishDetailModal;