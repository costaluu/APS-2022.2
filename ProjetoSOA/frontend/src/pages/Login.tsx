import React, { useRef } from "react";
import axios from "axios";
import { z } from "zod";
import useForm from "../useForm";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export const credentialsSchema = z.object({
    login: z.string(),
    senha: z.string(),
});

export type Credentials = z.infer<typeof credentialsSchema>;

export default function Login() {
    const emailRef = useRef();
    const passRef = useRef();

    const handleSubmit = async () => {
        const form = useForm(
            credentialsSchema,
            async (parsedData) => {
                try {
                    const res = await axios.post("http://localhost:8080/conta/login", parsedData);

                    if (res.status >= 200 && res.status < 300) toast.success("Logado!");
                    else toast.error("Credenciais Invalidas.");
                } catch (e) {
                    toast.error("Algo deu errado :/");
                }
            },
            () => {
                toast.error("Verifique os campos.");
            }
        );

        const obj = {
            login: (emailRef.current as any).value,
            senha: (passRef.current as any).value,
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <ToastContainer></ToastContainer>
            <div className="w-72 h-[17rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">E-Commerce Login</span>
                <span className="text-gray-900">Login</span>
                <input
                    ref={emailRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                <span className="text-gray-900">Password</span>
                <input
                    ref={passRef as any}
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                <button
                    onClick={() => handleSubmit()}
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Login
                </button>
                <div className="flex w-full justify-end">
                    <a
                        href="/cadastro"
                        className="cursor-pointer text-sm text-gray-00 hover:underline">
                        Cadastrar
                    </a>
                </div>
            </div>
        </div>
    );
}
