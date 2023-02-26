import React from "react";

export default function Item() {
    const imgSrc: string = `https://i.ibb.co/jfBrvxf/Capturar.png`;
    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[36rem] h-[32rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Cadeira gamer</span>
                <div className="w-full flex justify-center items-center bg-gray-300">
                    <img src={imgSrc} className="w-72 object-contain" />
                </div>
                <span className="text-lg font-semibold">R$ 1000</span>
                <span className="text-gray-600 text-base">Excelente cadeira gamer, repasse.</span>
                <span className="text-gray-900">Credenciais</span>
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
                <button
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Adicionar ao carrinho
                </button>
            </div>
        </div>
    );
}
