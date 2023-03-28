import React, { useRef, useState } from "react";
import axios from "axios";
import { z } from "zod";
import useForm from "../useFOrm";

export default function Login() {
    const emailRef = useRef();
    const passRef = useRef();
    const [data, setData] = useState<number | undefined>(undefined);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | undefined>(undefined);

    let placeHolder = <></>;

    if (loading) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-blue-400 text-blue-700 bg-blue-100">
                Loading...
            </div>
        );
    }

    if (error) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-red-400 text-red-700 bg-red-100">
                {error}
            </div>
        );
    }

    if (data == 200) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-green-400 text-green-700 bg-green-100">
                Logged!
            </div>
        );
    }

    const handleSubmit = async () => {
        const schema = z.object({
            login: z.string().email().min(1),
            senha: z.string().min(1),
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                setLoading(true);
                setData(undefined);

                try {
                    const res = await axios.post("http://localhost:8082/conta/login", parsedData);

                    if (res.status !== 200) setError("Credenciais Invalidas");
                    else setData(res.status);
                } catch (e) {
                    setError("Ocorreu um erro.");
                }

                setLoading(false);
            },
            () => {
                setError("Verifique os campos.");
                setLoading(false);
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
            <div className="w-72 h-[20rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">E-Commerce Login</span>
                {placeHolder}
                <span className="text-gray-900">Email</span>
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
