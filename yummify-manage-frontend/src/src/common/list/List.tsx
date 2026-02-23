import React from "react";
import ListElement from "./ListElement.tsx";

export interface ListProps<T> {
    items: T[];
    onItemClick?: (item: T) => void;
    renderItem: (item: T) => React.ReactNode;
}

function List<T>(props: ListProps<T>) {
    const {
        items,
        onItemClick,
        renderItem
    } = props;

    return (
        <div
            style={{
                display: "grid",
            }}
        >
            {items.map((item, i) =>
                <ListElement
                    key={i}
                    item={item}
                    render={renderItem}
                    onClick={onItemClick}
                />
            )}
        </div>
    );

}

export default List;