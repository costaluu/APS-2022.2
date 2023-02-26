import "../global.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Cadastro from "./pages/Cadastro";
import Login from "./pages/Login";
import PublicarItem from "./pages/PublicarItem";
import Item from "./pages/Item";
import Carrinho from "./pages/Carrinho";
import { QueryClient, QueryClientProvider } from "react-query";
const queryClient = new QueryClient();

function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <BrowserRouter>
                <Routes>
                    <Route path="/cadastro" element={<Cadastro></Cadastro>}></Route>
                    <Route path="/login" element={<Login></Login>}></Route>
                    <Route path="/publicar" element={<PublicarItem></PublicarItem>}></Route>
                    <Route path="/item" element={<Item></Item>}></Route>
                    <Route path="/carrinho" element={<Carrinho></Carrinho>}></Route>
                </Routes>
            </BrowserRouter>
        </QueryClientProvider>
    );
}

export default App;
