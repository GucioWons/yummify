import {DefaultValues, FieldValues, FormProvider, useForm} from "react-hook-form";
import React from "react";
import AppFormButtons from "./buttons/AppFormButtons.tsx";
import "./AppForm.css";


export interface AppFormProps<T extends FieldValues> {
    initialData: DefaultValues<T>;
    onSubmit: (data: T) => void;
    children: React.ReactNode;
}

function AppForm<T extends FieldValues>(props: AppFormProps<T>) {
    const {initialData, onSubmit, children} = props;

    const methods = useForm<T>({ defaultValues: initialData });

    return (
        <FormProvider {...methods}>
            <form onSubmit={methods.handleSubmit(onSubmit)}>
                <div className="app-form">
                    {children}
                    <AppFormButtons />
                </div>
            </form>
        </FormProvider>
    );
}

export default AppForm;