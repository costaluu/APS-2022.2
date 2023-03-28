import axios from "axios";
import React, { useRef, useState } from "react";
import { z } from "zod";
import useForm from "../useFOrm";

export default function Item() {
    const imgSrc: string = `https://i.pinimg.com/originals/d3/51/cb/d351cbd7bf0814648f9949cd4eba8be8.jpg`;

    const emailRef = useRef();
    const passRef = useRef();
    const quantityRef = useRef();
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

    if (data) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-green-400 text-green-700 bg-green-100">
                Item(s) adicionado(s)!
            </div>
        );
    }

    const handleSubmit = async () => {
        const schema = z.object({
            login: z.string().email().min(1),
            senha: z.string().min(1),
            quantidade: z.preprocess((a) => parseInt(z.string().parse(a), 10), z.number()),
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                setLoading(true);
                setData(undefined);

                try {
                    const res = await axios.post("http://localhost:8082/carrinho/adicionar", {
                        ...parsedData,
                        idProduto: "1",
                    });

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
            quantidade: (quantityRef.current as any).value,
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[36rem] h-[34rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Shrek</span>
                <div className="w-full flex justify-center items-center bg-gray-300">
                    <img src={imgSrc} className="w-72 object-contain" />
                </div>
                <span className="text-lg font-semibold">R$ 1000</span>
                <span className="text-gray-600 text-base">Shrek.</span>
                <span className="text-gray-900">Credenciais</span>
                <div className="w-full flex flex-row space-x-2">
                    <input
                        ref={emailRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Email"></input>
                    <input
                        ref={passRef as any}
                        type="password"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Passowrd"></input>
                </div>
                <div className="w-full flex flex-row space-x-2 items-center">
                    <span className="text-gray-600 text-base">Quantidade</span>
                    <input
                        ref={quantityRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Quantidade"></input>
                </div>
                {placeHolder}
                <button
                    onClick={() => handleSubmit()}
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Adicionar ao carrinho
                </button>
            </div>
        </div>
    );
}
