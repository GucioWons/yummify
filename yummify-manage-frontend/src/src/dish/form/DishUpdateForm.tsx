import DishForm from "./DishForm.tsx";
import {useCallback, useState} from "react";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {dishService} from "../service/dishService.ts";
import {AxiosResponse} from "axios";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;

export interface DishUpdateFormProps {
    dish: DishManageDto;
    image?: File;
    onCancel: () => void;
}

function DishUpdateForm(props: DishUpdateFormProps) {
    const {dish, image, onCancel} = props;

    const [uploadedImage, setUploadedImage] = useState<File | undefined>(image);

    const queryClient = useQueryClient();

    const handleUpdate = useMutation({
        mutationFn: (data: DishManageDto) => dishService.updateDish(data),
        onSuccess: (data: AxiosResponse<DishManageDto>) => handleUpdateSuccess(data.data),
        onError: (error) => console.error("Unexpected error:", error),
    });

    const handleUpdateSuccess = useCallback((data: DishManageDto) => {
        if (uploadedImage && uploadedImage !== image) {
            dishService.updateImage(data.id, uploadedImage)
                .catch((error) => console.error("Unexpected error:", error))
        }
        queryClient.invalidateQueries({queryKey: ["dishes"]})
            .then(() => onCancel());
    }, [image, uploadedImage, onCancel, queryClient]);

    return (
        <DishForm
            dish={dish}
            imageFile={uploadedImage}
            setImageFile={setUploadedImage}
            onCancel={onCancel}
            handleSubmit={handleUpdate.mutate}
        />
    );
}

export default DishUpdateForm;