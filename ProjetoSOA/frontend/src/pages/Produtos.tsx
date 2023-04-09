import React from "react";
import { z } from "zod";
import { Produto } from "./Produto";
import { useNavigate } from "react-router-dom";
import useFetch from "../useFetch";

export const credentialsSchema = z.object({
    login: z.string(),
    senha: z.string(),
});

export type Credentials = z.infer<typeof credentialsSchema>;

export default function ProdutosPage() {

    const navigate = useNavigate();

    const { data, error } = useFetch<Produto[]>(
        "http://localhost:8080/produto"
    );

    if (error || !data) {
        return (
            <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
                <div className="w-3/4 h-5/6 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto mb-4">
                    <span className="text-lg font-semibold">Produtos</span>
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

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-3/4 h-5/6 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto mb-4">
                <span className="text-lg font-semibold">Produtos</span>
                {data.map((produto) => {
                    return (
                        <div
                            onClick={() => navigate(`/produto/${produto.id}`)}
                            key={produto.id}
                            className="cursor-pointer border border-gray-300 rounded-md p-2 hover:bg-gray-100"
                        >
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
                    );
                })}
            </div>
        </div>
    );
}
