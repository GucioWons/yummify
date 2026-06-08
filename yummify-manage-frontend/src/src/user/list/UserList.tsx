import {Dtos} from "../../common/dtos.ts";
import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import List from "../../common/list/List.tsx";
import UserListElement from "../../user/list/UserListElement.tsx";
import {userService} from "../service/userService.ts";
import UserManageDto = Dtos.UserManageDto;

export interface UserListProps {
    onElementClick: (dish: UserManageDto) => void;
}

function UserList(props: UserListProps) {
    const {onElementClick} = props;

    const {data: users, isLoading, isError} = useQuery<UserManageDto[]>({
        queryKey: ["users"],
        queryFn: () => userService.getUsers().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania użytkowników.</div>;

    return (
        <List
            items={users!}
            renderItem={(user) => <UserListElement user={user}/>}
            onItemClick={onElementClick}
        />
    );
}

export default UserList;