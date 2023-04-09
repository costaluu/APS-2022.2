import axios from "axios";
import React, { useRef } from "react";
import useForm from "../useForm";
import { Produto, produtoSchema } from "./Produto";
import { nanoid } from "nanoid";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function PublicarItem() {
    const emailRef = useRef();
    const nameRef = useRef();
    const descriptionRef = useRef();
    const priceRef = useRef();
    const totalRef = useRef();

    const handleSubmit = async () => {
        const obj: Produto = {
            id: nanoid(),
            dono: (emailRef.current as any).value,
            nome: (nameRef.current as any).value,
            descricao: (descriptionRef.current as any).value,
            valor: parseFloat((priceRef.current as any).value),
            totalUnidades: parseInt((totalRef.current as any).value),
        };

        try {
            const result = produtoSchema.parse(obj);
            try {
                const res = await axios.post("http://localhost:8080/produto/publicar", result);

                if (res.status !== 200) toast.error("Algo deu errado :/");
                else toast.success("Produto publicado!");
            } catch (e) {
                toast.error("Algo deu errado :/");
            }
        } catch (error) {
            console.log(error)
            toast.error("Verifique os campos.");
        }
    };

    return (
        <div className="w-screen h-screen m-0 p-0 bg-gradient-to-r from-rose-100 to-teal-100 flex justify-center items-center">
            <ToastContainer></ToastContainer>
            <div className="w-[36rem] h-[23rem] bg-white rounded-lg shadow-lg flex flex-col space-y-2 p-4">
                <span className="text-lg font-semibold">Publicar Item</span>
                <span className="text-gray-900">Credencial</span>
                <div className="w-full flex flex-row space-x-2">
                    <input
                        ref={emailRef as any}
                        type="text"
                        className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                        placeholder="Email"
                    ></input>
                </div>
                <span className="text-gray-900">Produto</span>

                <input
                    ref={nameRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Nome"
                ></input>
                <input
                    ref={descriptionRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Descrição"
                ></input>
                <input
                    ref={priceRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Preço"
                ></input>
                <input
                    ref={totalRef as any}
                    type="text"
                    className="px-2 py-1 w-full border border-gray-300 text-gray-800 rounded-md outline-none"
                    placeholder="Total de unidades"
                ></input>
                <button
                    onClick={() => handleSubmit()}
                    type="button"
                    className="px-2 py-1 w-full bg-teal-400 rounded-md text-white"
                >
                    Publicar
                </button>
            </div>
        </div>
    );
}
