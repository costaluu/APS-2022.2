import React from "react";

// const login = async () => {
//     const user = {
//         login: "teste2",
//         senha: "teste2",
//     }
//     const res = await axios.post("http://localhost:8082/conta/cadastrar", user);
//     if(res.status !== 200) return Promise.reject();
//     return res.status;
// };

export default function Cadastro() {
    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <div className="w-72 h-72 bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">E-Commerce Cadastro</span>
                <span className="text-gray-900">Email</span>
                <input
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                <span className="text-gray-900">Password</span>
                <input
                    type="password"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"></input>
                <button
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white">
                    Cadastrar
                </button>
                <div className="flex w-full justify-end">
                    <span className="cursor-pointer text-sm text-gray-00 hover:underline">
                        Login
                    </span>
                </div>
            </div>
        </div>
    );
}
