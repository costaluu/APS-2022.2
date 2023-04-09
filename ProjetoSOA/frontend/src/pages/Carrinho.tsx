import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Produto } from "./Produto";

interface CarrinhoResponse {
    produto: Produto;
    quantidade: number;
}

export default function Item() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState<CarrinhoResponse[] | undefined>(undefined);

    useEffect(() => {
        if (data) return;

        const controller = new AbortController();

        const loader = async () => {
            try {
                const result = await axios.get(`http://localhost:8080/carrinho/${id}`);

                if (result.status >= 300) {
                    toast.error("Algo deu errado :/");

                    return;
                }

                let temp: CarrinhoResponse[] = [];

                Object.values(result.data.produtos).forEach((carrinhoResponse: any) => {
                    temp.push(carrinhoResponse);
                });

                setData(temp);
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
                    <span className="text-lg font-semibold">Carrinho de {id}</span>
                    {new Array(5).fill(0).map((_, index) => {
                        return (
                            <div key={index} className="space-y-2">
                                <div className="flex flex-row items-baseline space-x-2">
                                    <div
                                        role="status"
                                        className="flex h-8 w-36 animate-pulse rounded-md bg-gray-200"></div>
                                    <div
                                        role="status"
                                        className="flex h-5 w-24 animate-pulse rounded-md bg-gray-200"></div>
                                </div>
                                <div
                                    role="status"
                                    className="flex h-5 w-full animate-pulse rounded-md bg-gray-200"></div>
                                <div
                                    role="status"
                                    className="flex h-5 w-full animate-pulse rounded-md bg-gray-200"></div>
                                <div className="flex flex-row items-baseline justify-between">
                                    <div
                                        role="status"
                                        className="flex h-5 w-14 animate-pulse rounded-md bg-gray-200"></div>
                                    <div
                                        role="status"
                                        className="flex h-5 w-24 animate-pulse rounded-md bg-gray-200"></div>
                                </div>
                            </div>
                        );
                    })}
                </div>
            </div>
        );
    }

    const handleUpdateProduct = async (productId: string, quantity: number) => {
        try {
            const result = await axios.put("http://localhost:8080/carrinho/atualizar", {
                login: id,
                idProduto: productId,
                quantidade: quantity,
            });

            if (result.status > 300) {
                toast.error("Algo deu errado :/");
            }

            toast.success("Produto atualizado!");
        } catch (e) {
            toast.error("Algo deu errado :/");
        }
    };

    const handleRemoveProduct = async (productId: string) => {
        try {
            const result = await axios.delete("http://localhost:8080/carrinho/remover", {
                data: {
                    login: id,
                    idProduto: productId,
                },
            });

            if (result.status != 200) {
                toast.error("Algo deu errado :/");
            } else {
                setData(data.filter((prod) => prod.produto.id != productId));
                toast.success("Produto deletado!");
            }
        } catch (e) {
            toast.error("Algo deu errado :/");
        }
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <ToastContainer></ToastContainer>
            <div className="w-3/4 h-5/6 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto mb-4">
                <span className="text-lg font-semibold">Carrinho de {id}</span>
                {data.map((produto, index) => {
                    return (
                        <div
                            key={produto.produto.id}
                            className="border border-gray-300 rounded-md p-2 hover:bg-gray-50 flex flex-row justify-between">
                            <div className="flex-grow">
                                <div
                                    onClick={() => navigate(`/produto/${produto.produto.id}`)}
                                    className="flex flex-row items-baseline space-x-2 cursor-pointer">
                                    <span className="text-2xl font-semibold">
                                        {produto.produto.nome}
                                    </span>
                                    <span className="text-base text-gray-400">
                                        vendido por: {produto.produto.dono}
                                    </span>
                                </div>
                                <span
                                    onClick={() => navigate(`/produto/${produto.produto.id}`)}
                                    className="text-gray-600 text-base cursor-pointer">
                                    {produto.produto.descricao}
                                </span>
                                <div className="flex flex-row items-baseline justify-between">
                                    <span className="text-lg font-semibold">
                                        R$ {produto.produto.valor}
                                    </span>
                                    <div className="flex flex-row space-x-2 items-center">
                                        <span className="text-base">Quantidade:</span>
                                        <input
                                            type="number"
                                            className="border border-gray-300 rounded-md focus:ring-0 focus:outline-none px-2 text-sm py-0.5"
                                            value={produto.quantidade}
                                            onChange={(event) => {
                                                setData((currentData) => {
                                                    if (!currentData) {
                                                        return currentData;
                                                    }

                                                    let newData = currentData;
                                                    newData[index].quantidade = parseInt(
                                                        event.target.value
                                                    );

                                                    return [...newData];
                                                });
                                            }}></input>
                                        <button
                                            onClick={() =>
                                                handleUpdateProduct(
                                                    produto.produto.id,
                                                    data[index].quantidade
                                                )
                                            }
                                            type="button"
                                            className="bg-sky-500 rounded-md p-1 flex-initial text-white ml-6 hover:bg-sky-600 text-sm">
                                            Atualizar
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <button
                                onClick={() => handleRemoveProduct(produto.produto.id)}
                                type="button"
                                className="bg-red-500 rounded-md p-2 flex-initial text-white ml-6 hover:bg-red-600">
                                Deletar Produto
                            </button>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}
