import "../global.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Cadastro from "./pages/Cadastro";
import Login from "./pages/Login";
import PublicarItem from "./pages/PublicarItem";
import Produto from "./pages/Produto";
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
                    <Route path="/produto/:id" element={<Produto></Produto>}></Route>
                    <Route path="/carrinho" element={<Carrinho></Carrinho>}></Route>
                    <Route path="/404" element={<div>404 - Not found</div>}></Route>
                </Routes>
            </BrowserRouter>
        </QueryClientProvider>
    );
}

export default App;
