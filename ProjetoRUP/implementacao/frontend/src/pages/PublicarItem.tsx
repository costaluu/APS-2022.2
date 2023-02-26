import React from "react";

// const publicarItem = () => {
//     const produto = {
//         id: "1",
//         dono: "teste",
//         nome: "Água",
//         descricao: "Água pra beber",
//         totalUnidades: 100,
//         valor: 1.50
//     }
//     const res = await axios.post("http://localhost:8082/produto/publicar", produto);
// }

export default function PublicarItem() {
    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-[36rem] h-[24rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Publicar Item</span>
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
                <span className="text-gray-900">Produto</span>
                <input
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Nome"></input>
                <input
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Descrição"></input>
                <input
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Preço"></input>
                <input
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="URL Imagem"></input>
                <button
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Publicar
                </button>
            </div>
        </div>
    );
}
