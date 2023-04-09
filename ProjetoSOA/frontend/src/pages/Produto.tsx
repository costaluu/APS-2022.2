import axios from "axios";
import React, { useRef } from "react";
import { z } from "zod";
import useForm from "../useForm";
import { useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import useFetch from "../useFetch";

export const produtoSchema = z.object({
    id: z.string(),
    dono: z.string(),
    nome: z.string().min(1),
    descricao: z.string().min(1),
    valor: z.number(),
    totalUnidades: z.number(),
});

export type Produto = z.infer<typeof produtoSchema>;

export default function PageProduto() {
    const { id } = useParams();

    const emailRef = useRef();
    const quantityRef = useRef();
    const { data, error } = useFetch<Produto>(
        `http://localhost:8080/produto/${id}?quantidade=1`
    );

    const handleAddToCart = async () => {
        const schema = z.object({
            login: z.string().min(1),
            quantidade: z.number()
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                try {
                    const res = await axios.post(
                        "http://localhost:8080/carrinho/adicionar",
                        {
                            idProduto: id!,
                            ...parsedData,
                        }
                    );

                    if (res.status !== 200) toast.error("Algo deu errado :/");
                    else toast.success("Produto adicionado ao carrinho!");
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
            quantidade: parseInt((quantityRef.current as any).value),
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <ToastContainer></ToastContainer>
            <div className="w-[36rem] h-[22rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto scrollbar-thin">
                {!data || error ? (
                    <>
                        <div className="flex flex-row items-baseline space-x-2">
                            <div
                                role="status"
                                className="flex h-8 w-36 animate-pulse rounded-md bg-gray-200"
                            ></div>
                            <div
                                role="status"
                                className="flex h-5 w-24 animate-pulse rounded-md bg-gray-200"
                            ></div>
                        </div>
                        <div
                            role="status"
                            className="flex h-5 w-full animate-pulse rounded-md bg-gray-200"
                        ></div>
                        <div
                            role="status"
                            className="flex h-5 w-full animate-pulse rounded-md bg-gray-200"
                        ></div>
                        <div className="flex flex-row items-baseline justify-between">
                            <div
                                role="status"
                                className="flex h-5 w-14 animate-pulse rounded-md bg-gray-200"
                            ></div>
                            <div
                                role="status"
                                className="flex h-5 w-24 animate-pulse rounded-md bg-gray-200"
                            ></div>
                        </div>
                    </>
                ) : (
                    <>
                        <div className="flex flex-row items-baseline space-x-2">
                            <span className="text-2xl font-semibold">
                                {data.nome}
                            </span>
                            <span className="text-base text-gray-400">
                                vendido por: {data.dono}
                            </span>
                        </div>
                        <span className="text-gray-600 text-base">
                            {data.descricao}
                        </span>
                        <div className="flex flex-row items-baseline justify-between">
                            <span className="text-lg font-semibold">
                                R$ {data.valor}
                            </span>
                            <span className="text-base">
                                Quantidade: {data.totalUnidades}
                            </span>
                        </div>
                    </>
                )}

                <span className="text-gray-900">Credencial</span>
                <div className="w-full flex flex-row space-x-2">
                    <input
                        ref={emailRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Email"
                    ></input>
                </div>
                <div className="w-full flex flex-row space-x-2 items-center">
                    <span className="text-gray-600 text-base">Quantidade</span>
                    <input
                        ref={quantityRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Quantidade"
                    ></input>
                </div>
                <button
                    onClick={() => handleAddToCart()}
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white"
                >
                    Adicionar ao carrinho
                </button>
            </div>
        </div>
    );
}
