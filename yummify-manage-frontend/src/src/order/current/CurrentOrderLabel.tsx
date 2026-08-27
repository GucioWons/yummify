import {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";

export interface CurrentOrderLabel {
    text: string;
    icon: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
    color: 'BLUE' | 'YELLOW' | 'ORANGE' | 'GREEN' | 'RED' | 'GREY'
}

function CurrentOrderLabel(props: CurrentOrderLabel) {
    const {text, icon} = props;
    return (
        <div>
            {text}
        </div>
    )

}

export default CurrentOrderLabel;