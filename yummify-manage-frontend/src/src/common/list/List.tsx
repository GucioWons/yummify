import React from "react";
import ListElement from "./ListElement.tsx";

export interface ListProps<T> {
    items: T[];
    columns: number;
    onItemClick?: (item: T) => void;
    renderItem: (item: T) => React.ReactNode;
}

function List<T>(props: ListProps<T>) {
    const {
        items,
        columns,
        onItemClick,
        renderItem
    } = props;

    return (
        <div
            style={{
                display: "grid",
                gridTemplateColumns: `repeat(${columns}, 1fr)`,
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