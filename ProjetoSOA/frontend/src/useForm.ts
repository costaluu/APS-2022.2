import { z } from "zod";

const useForm = async <T>(
    schema: z.Schema<T>,
    onSubmit: (values: T) => void | Promise<void>,
    onError: () => void
) => {
    return {
        onSubmit: (values: any) => {
            try {
                schema.parse(values);
                onSubmit(values);
            } catch (e) {
                onError();
            }
        },
    };
};

export default useForm;
