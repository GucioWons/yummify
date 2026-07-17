import Modal from "../../common/modal/Modal.tsx";
import {Dtos} from "../../common/dtos.ts";
import RoleManageDto = Dtos.RoleManageDto;
import RoleDisplay from "./RoleDisplay.tsx";
import {useQuery} from "@tanstack/react-query";
import {roleService} from "../service/roleService.ts";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";

export interface RoleDisplayModalProps {
    id: string;
    onClose: () => void;
}

function RoleDisplayModal(props: RoleDisplayModalProps) {
    const {id, onClose} = props;

    const {data: role, isLoading: isRoleLoading, isError: isRoleError} = useQuery<RoleManageDto>({
        queryKey: ["roles", id],
        queryFn: () => roleService.getRole(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isRoleLoading) return <LoadingSpinner />;
    if (isRoleError) return <div>Błąd podczas pobierania roli.</div>;

    return (
        <Modal title="Role details" onClose={onClose}>
            <RoleDisplay
                role={role!}
                onCloseClick={onClose}
            />
        </Modal>
    );
}

export default RoleDisplayModal;