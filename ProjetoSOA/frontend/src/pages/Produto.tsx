import axios from "axios";
import React, { ReactNode, useEffect, useRef, useState } from "react";
import { z } from "zod";
import useForm from "../useFOrm";
import { useNavigate, useParams } from "react-router-dom";

const produtoSchema = z.object({
    id: z.string(),
    dono: z.string(),
    nome: z.string(),
    descricao: z.string(),
    totalUnidade: z.number(),
    valor: z.number(),
});

type Produto = z.infer<typeof produtoSchema>;

export default function Produto() {
    const { id } = useParams();

    const emailRef = useRef();
    const quantityRef = useRef();
    const [product, setProduct] = useState<Produto | undefined>(undefined);
    const [data, setData] = useState<number | undefined>(undefined);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | undefined>(undefined);
    const [placeHolder, setPlaceHolder] = useState<ReactNode>(<></>);

    const navigate = useNavigate();

    useEffect(() => {
        if (loading) {
            setPlaceHolder(
                <div className="px-2 py-1 border-l-4 border-blue-400 text-blue-700 bg-blue-100">
                    <div className="flex flex-row space-x-2 items-center">
                        <span>Loading...</span>
                        <div className="h-3 w-3 animate-bounce rounded-full bg-blue-500"></div>
                        <div className="h-3 w-3 animate-bounce rounded-full bg-blue-500 delay-100"></div>
                        <div className="h-3 w-3 animate-bounce rounded-full bg-blue-500 delay-200"></div>
                    </div>
                </div>
            );
        }

        if (error) {
            setPlaceHolder(
                <div className="px-2 py-1 border-l-4 border-red-400 text-red-700 bg-red-100">
                    {error}
                </div>
            );
        }

        if (data == 200) {
            setPlaceHolder(
                <div className="px-2 py-1 border-l-4 border-green-400 text-green-700 bg-green-100">
                    Logged!
                </div>
            );
        }

        if (!product && id) {
            const loader = async () => {
                const res = await axios.get(`http://localhost:8080/produto?${id}?quantidade=1`);

                if (res.status == 404) {
                    navigate("/404");

                    return;
                }

                setProduct(res.data);
            };

            loader();
        }
    }, [loading, error, error]);

    const handleSubmit = async () => {
        setLoading(true);
        setData(undefined);
        setError(undefined);

        const schema = z.object({
            login: z.string().email().min(1),
            quantidade: z.preprocess((a) => parseInt(z.string().parse(a), 10), z.number()),
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                try {
                    const res = await axios.post("http://localhost:8080/carrinho/adicionar", {
                        idProduto: id!,
                        ...parsedData,
                    });

                    if (res.status !== 200) setError("Ocorreu um erro");
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
            quantidade: (quantityRef.current as any).value,
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[36rem] h-[22rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4 overflow-y-auto scrollbar-thin">
                {placeHolder}
                {!product ? (
                    <>
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
                    </>
                ) : (
                    <>
                        <div className="flex flex-row items-baseline space-x-2">
                            <span className="text-2xl font-semibold">{product.nome}</span>
                            <span className="text-base text-gray-400">
                                vendido por: {product.dono}
                            </span>
                        </div>
                        <span className="text-gray-600 text-base">{product.descricao}</span>
                        <div className="flex flex-row items-baseline justify-between">
                            <span className="text-lg font-semibold">R$ {product.valor}</span>
                            <span className="text-base">Quantidade: {product.totalUnidade}</span>
                        </div>
                    </>
                )}

                <span className="text-gray-900">Credenciais</span>
                <div className="w-full flex flex-row space-x-2">
                    <input
                        ref={emailRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Email"></input>
                </div>
                <div className="w-full flex flex-row space-x-2 items-center">
                    <span className="text-gray-600 text-base">Quantidade</span>
                    <input
                        ref={quantityRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Quantidade"></input>
                </div>
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
