import axios from "axios";
import React, { useRef, useState } from "react";
import { useQuery } from "react-query";
import { z } from "zod";
import useForm from "../useFOrm";

const checkout = async () => {
    const checkoutInfo = {
        login: "teste2",
        numCartao: "1",
        codSeguranca: 1,
        validade: "1",
        nomeNoCartao: "1",
    };
    const res = await axios.post("http://localhost:8082/carrinho/pagamento", checkoutInfo);
    return res.status;
};

export default function Item() {
    const imgSrc: string = `https://i.pinimg.com/originals/d3/51/cb/d351cbd7bf0814648f9949cd4eba8be8.jpg`;
    const [pagamentoCartao, setPagamentoCartao] = useState<boolean>(true);

    const emailRef = useRef();
    const passRef = useRef();
    const cardNameRef = useRef();
    const cardNumberRef = useRef();
    const cardExpirationDateRef = useRef();
    const cardSecurityCodeRef = useRef();

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
                Pedido criado!
            </div>
        );
    }

    const handleSubmit = async () => {
        const schema = z.object({
            login: z.string().email().min(1),
            senha: z.string().min(1),
            numCartao: z.string().min(1),
            nomeNoCartao: z.string().min(1),
            validade: z.string().length(3),
            codSeguranca: z.string(),
        });

        const form = useForm(
            schema,
            async (parsedData) => {
                setLoading(true);
                setData(undefined);

                try {
                    const res = await axios.post(
                        "http://localhost:8082/carrinho/pagamento",
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
            numCartao: (cardNumberRef.current as any).value,
            nomeNoCartao: (cardNameRef.current as any).value,
            validade: (cardExpirationDateRef.current as any).value,
            codSeguranca: (cardSecurityCodeRef.current as any).value,
        };

        (await form).onSubmit(obj);
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[40rem] h-[44rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Carrinho</span>
                <div className="flex flex-row space-x-2 items-center">
                    <div className="p-2 border border-gray-300 rounded-md">
                        <img src={imgSrc} className="w-12 object-contain" />
                    </div>
                    <div className="flex flex-col space-y-1 flex-grow">
                        <span className="text-lg font-semibold">Shrek</span>
                        <span className="text-md text-gray-900">13 em estoque</span>
                    </div>
                    <div className="flex flex-col space-y-1 justify-center item-center">
                        <span className="text-lg font-semibold">Quantidade</span>
                        <span className="text-md text-gray-900">2</span>
                    </div>
                    <div className="flex flex-col space-y-1 justify-center item-center">
                        <span className="text-lg font-semibold">Preço</span>
                        <span className="text-md text-gray-900">R$ 1000</span>
                    </div>
                </div>
                <div className="w-full flex flex-col justify-end border-t border-gray-300">
                    <span className="font-semibold place-self-end">Total</span>
                    <span className="text-gray-800 place-self-end">R$ 2000</span>
                </div>
                <span>Credenciais</span>
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
                <span>Método de pagamento</span>
                {placeHolder}
                <div className="flex flex-col space-y-2 w-full">
                    <div className="grid grid-cols-2">
                        <button
                            onClick={() => setPagamentoCartao(true)}
                            type="button"
                            className={
                                "font-semibold px-2 py-2 rounded-md hover:bg-gray-100" +
                                (pagamentoCartao ? " bg-gray-100" : "")
                            }>
                            Cartão
                        </button>
                        <button
                            onClick={() => setPagamentoCartao(false)}
                            type="button"
                            className={
                                "font-semibold px-2 py-2 rounded-md hover:bg-gray-100" +
                                (pagamentoCartao ? "" : " bg-gray-100")
                            }>
                            PIX
                        </button>
                    </div>
                    {pagamentoCartao ? (
                        <>
                            <span className="text-gray-900">Nome no cartão</span>
                            <input
                                ref={cardNameRef as any}
                                type="text"
                                className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                            <span>Informação do cartão</span>
                            <div className="flex flex-row space-x-2">
                                <input
                                    ref={cardNumberRef as any}
                                    type="text"
                                    className="flex-grow px-2 py-1 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="Número do cartão"></input>
                                <input
                                    ref={cardExpirationDateRef as any}
                                    type="text"
                                    className="px-2 py-1 w-20 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="Validade"></input>
                                <input
                                    ref={cardSecurityCodeRef as any}
                                    type="text"
                                    className="px-2 py-1 w-28 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="Código de segurança"></input>
                            </div>
                            <button
                                onClick={() => handleSubmit()}
                                type="button"
                                className="px-2 py-1 w-full bg-teal-400 rounded-md text-white hover:bg-teal-500">
                                Efetuar pagamento
                            </button>
                        </>
                    ) : (
                        <>
                            <button
                                onClick={() => handleSubmit()}
                                type="button"
                                className="px-2 py-1 w-full bg-teal-400 rounded-md text-white hover:bg-teal-500">
                                Efetuar pagamento
                            </button>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}
