import React from "react";
import {Bars} from "react-loader-spinner";

export interface LoadingSpinnerProps {
    height?: number;
    width?: number;
}

function LoadingSpinner(props: LoadingSpinnerProps) {
    const {height = 80, width = 80} = props;

    return (
        <Bars
            visible
            height={height}
            width={width}
            ariaLabel="bars-loading"
            wrapperStyle={{
                display: "flex",
                justifyContent: "center",
            }}
            wrapperClass="bars-wrapper"
            color="#059669"
        />
    );
}

export default LoadingSpinner;