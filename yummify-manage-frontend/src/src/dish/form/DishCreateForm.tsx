import {useMutation, useQueryClient} from "@tanstack/react-query";
import {dishService} from "../service/dishService.ts";
import {AxiosResponse} from "axios";
import {useCallback, useState} from "react";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import DishForm from "./DishForm.tsx";

export interface DishCreateFormProps {
    onCancel: () => void;
}

function DishCreateForm(props: DishCreateFormProps) {
    const {onCancel} = props;

    const [uploadedImage, setUploadedImage] = useState<File | undefined>();

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: DishManageDto) => dishService.createDish(data),
        onSuccess: (data: AxiosResponse<DishManageDto>) => handleCreateSuccess(data.data),
        onError: (error) => console.error("Unexpected error:", error),
    });

    const handleCreateSuccess = useCallback((data: DishManageDto) => {
        if (uploadedImage) {
            dishService.updateImage(data.id, uploadedImage)
                .catch((error) => console.error("Unexpected error:", error))
        }
        queryClient.invalidateQueries({queryKey: ["dishes"]})
            .then(() => onCancel());
    }, [uploadedImage, onCancel, queryClient]);

    return (
        <DishForm
            imageFile={uploadedImage}
            setImageFile={setUploadedImage}
            onCancel={onCancel}
            handleSubmit={handleCreate.mutate}
        />
    );
}

export default DishCreateForm;