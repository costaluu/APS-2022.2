import axios from "axios";
import React, { useState } from "react";
import { useQuery } from "react-query";

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
    const imgSrc: string = `https://i.ibb.co/jfBrvxf/Capturar.png`;
    const [pagamentoCartao, setPagamentoCartao] = useState<boolean>(false);

    const { data, error, isLoading, refetch } = useQuery("publicarItem", checkout, {
        enabled: false,
    });

    let placeHolder = <></>;

    if (isLoading) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-blue-400 text-blue-700 bg-blue-100">
                Loading...
            </div>
        );
    }

    if (error) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-red-400 text-red-700 bg-red-100">Error</div>
        );
    }

    if (data) {
        placeHolder = (
            <div className="px-2 py-1 border-l-4 border-green-400 text-green-700 bg-green-100">
                Logged!
            </div>
        );
    }

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[40rem] h-[44rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Carrinho</span>
                <div className="flex flex-row space-x-2 items-center">
                    <div className="p-2 border border-gray-300 rounded-md">
                        <img src={imgSrc} className="w-12 object-contain" />
                    </div>
                    <div className="flex flex-col space-y-1 flex-grow">
                        <span className="text-lg font-semibold">Cadeira gamer</span>
                        <span className="text-md text-gray-900">13 em estoque</span>
                    </div>
                    <div className="flex flex-col space-y-1 justify-center item-center">
                        <span className="text-lg font-semibold">Quantidade</span>
                        <span className="text-md text-gray-900">2</span>
                    </div>
                    <div className="flex flex-col space-y-1 justify-center item-center">
                        <span className="text-lg font-semibold">Pre??o</span>
                        <span className="text-md text-gray-900">R$ 1000</span>
                    </div>
                </div>
                <div className="w-full flex flex-col justify-end border-t border-gray-300">
                    <span className="font-semibold place-self-end">Total</span>
                    <span className="text-gray-800 place-self-end">R$ 2000</span>
                </div>
                {/* <span className="text-gray-900">CEP</span>
                <input
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                <span className="text-gray-900">N??mero</span>
                <input
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input> */}
                <span>Credenciais</span>
                <div className="w-full flex flex-row space-x-2">
                    <input
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Email"></input>
                    <input
                        type="password"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Passowrd"></input>
                </div>
                <span>M??todo de pagamento</span>
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
                            Cart??o
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
                            <span className="text-gray-900">Nome no cart??o</span>
                            <input
                                type="text"
                                className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                            <span>Informa????o do cart??o</span>
                            <div className="flex flex-row space-x-2">
                                <input
                                    type="text"
                                    className="flex-grow px-2 py-1 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="N??mero do cart??o"></input>
                                <input
                                    type="text"
                                    className="px-2 py-1 w-20 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="Validade"></input>
                                <input
                                    type="text"
                                    className="px-2 py-1 w-28 border border-gray-300 text-gray-800 rounded-md outline-none"
                                    placeholder="C??digo de seguran??a"></input>
                            </div>
                            <button
                                onClick={() => refetch()}
                                type="button"
                                className="px-2 py-1 w-full bg-teal-400 rounded-md text-white hover:bg-teal-500">
                                Efetuar pagamento
                            </button>
                        </>
                    ) : (
                        <>
                            <button
                                onClick={() => refetch()}
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
