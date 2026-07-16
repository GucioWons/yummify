import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import List from "../../common/list/List.tsx";
import {Dtos} from "../../common/dtos.ts";
import RoleListDto = Dtos.RoleListDto;
import {roleService} from "../service/roleService.ts";
import RoleListElement from "./RoleListElement.tsx";

export interface RoleListProps {
    onElementClick: (dish: RoleListDto) => void;
}

function RoleList(props: RoleListProps) {
    const {onElementClick} = props;

    const {data: roles, isLoading, isError} = useQuery<RoleListDto[]>({
        queryKey: ["roles"],
        queryFn: () => roleService.getRoles().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania ról.</div>;

    return (
        <List
            items={roles!}
            onItemClick={onElementClick}
            renderItem={(role) => <RoleListElement role={role}/>}
        />
    );
}

export default RoleList;