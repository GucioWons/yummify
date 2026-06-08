import {format} from "date-fns/format";
import {pl} from "date-fns/locale/pl";

export const formatDate = (
    date: Date | string | number,
    pattern = 'dd.MM.yyyy'
) => {
    return format(new Date(date), pattern, {
        locale: pl,
    });
};