import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Produto } from "./Produto";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function MeusProdutos() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState<Produto[] | undefined>(undefined);

    useEffect(() => {
        const controller = new AbortController();

        const loader = async () => {
            try {
                const result = await axios.get(
                    `http://localhost:8080/produto/meus-produtos/${id}`
                );

                if (result.status > 300) {
                    toast.error("Algo deu errado :/");

                    return;
                }

                setData(result.data);
            } catch (e) {
                toast.error("Algo deu errado :/");
                setData([]);
            }
        };

        loader();

        return () => controller.abort();
    }, []);

    if (!data) {
        return (
            <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
                <ToastContainer></ToastContainer>
                <div className="w-3/4 h-5/6 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto mb-4">
                    <span className="text-lg font-semibold">
                        Produtos de {id}
                    </span>
                    {new Array(5).fill(0).map((_, index) => {
                        return (
                            <div key={index} className="space-y-2">
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
                            </div>
                        );
                    })}
                </div>
            </div>
        );
    }

    const handleRemoveProduct = async (productId: string) => {
        try {
            const result = await axios.delete(
                `http://localhost:8080/produto/${productId}?login=${id}`
            );

            if (result.status > 300) {
                toast.error("Algo deu errado :/");

                return;
            }

            setData(data.filter((product) => product.id != productId));
        } catch (e) {
            toast.error("Algo deu errado :/");
        }
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <ToastContainer></ToastContainer>
            <div className="w-3/4 h-5/6 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto mb-4">
                <span className="text-lg font-semibold">Produtos de {id}</span>
                {data.map((produto) => {
                    return (
                        <div
                            onClick={() => navigate(`/produto/${produto.id}`)}
                            key={produto.id}
                            className="cursor-pointer border border-gray-300 rounded-md p-2 hover:bg-gray-100 flex flex-row justify-between"
                        >
                            <div className="flex-grow">
                                <div className="flex flex-row items-baseline space-x-2">
                                    <span className="text-2xl font-semibold">
                                        {produto.nome}
                                    </span>
                                    <span className="text-base text-gray-400">
                                        vendido por: {produto.dono}
                                    </span>
                                </div>
                                <span className="text-gray-600 text-base">
                                    {produto.descricao}
                                </span>
                                <div className="flex flex-row items-baseline justify-between">
                                    <span className="text-lg font-semibold">
                                        R$ {produto.valor}
                                    </span>
                                    <span className="text-base">
                                        Quantidade: {produto.totalUnidades}
                                    </span>
                                </div>
                            </div>
                            <button
                                onClick={() => handleRemoveProduct(produto.id)}
                                type="button"
                                className="bg-red-500 rounded-md p-2 flex-initial text-white ml-6 hover:bg-red-600"
                            >
                                Deletar Produto
                            </button>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}
