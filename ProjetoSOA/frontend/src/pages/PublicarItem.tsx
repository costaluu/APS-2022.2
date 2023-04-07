import axios from "axios";
import React, { useRef, useState } from "react";
import { useQuery } from "react-query";
import { z } from "zod";
import useForm from "../useFOrm";

const publicarItem = async () => {
    const produto = {
        id: "1",
        dono: "teste",
        nome: "Água",
        descricao: "Água pra beber",
        totalUnidades: 10,
        valor: 1.5,
    };
    const res = await axios.post("http://localhost:8082/produto/publicar", produto);
    return res.status;
};

export default function PublicarItem() {
    const emailRef = useRef();
    const passRef = useRef();
    const nameRef = useRef();
    const descriptionRef = useRef();
    const priceRef = useRef();
    const totalRef = useRef();
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
                Produto publicado!
            </div>
        );
    }

    const handleSubmit = async () => {
        const schema = z.object({
            login: z.string().email().min(1),
            senha: z.string().min(1),
            nome: z.string().min(1),
            descricao: z.string().min(1),
            valor: z.preprocess((a) => parseInt(z.string().parse(a), 10), z.number()),
            totalUnidades: z.preprocess((a) => parseInt(z.string().parse(a), 10), z.number()),
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                setLoading(true);
                setData(undefined);

                try {
                    const res = await axios.post(
                        "http://localhost:8082/produto/publicar",
                        parsedData
                    );

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
            nome: (nameRef.current as any).value,
            descricao: (descriptionRef.current as any).value,
            valor: (priceRef.current as any).value,
            totalUnidades: (totalRef.current as any).value,
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[36rem] h-[26rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Publicar Item</span>
                {placeHolder}
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
                <span className="text-gray-900">Produto</span>
                <input
                    ref={nameRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Nome"></input>
                <input
                    ref={descriptionRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Descrição"></input>
                <input
                    ref={priceRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Preço"></input>
                <input
                    ref={totalRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Total de unidades"></input>
                <button
                    onClick={() => handleSubmit()}
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Publicar
                </button>
            </div>
        </div>
    );
}
