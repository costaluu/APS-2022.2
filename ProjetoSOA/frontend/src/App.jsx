import "../global.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Cadastro from "./pages/Cadastro";
import Login from "./pages/Login";
import PublicarItem from "./pages/PublicarItem";
import Produto from "./pages/Produto";
import Carrinho from "./pages/Carrinho";
import ProdutosPage from "./pages/Produtos";
import MeusProdutos from "./pages/MeusProdutos";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/cadastro" element={<Cadastro></Cadastro>}></Route>
                <Route path="/login" element={<Login></Login>}></Route>
                <Route
                    path="/produtos"
                    element={<ProdutosPage></ProdutosPage>}
                ></Route>
                <Route
                    path="/produtos/:id"
                    element={<MeusProdutos></MeusProdutos>}
                ></Route>
                <Route
                    path="/publicar"
                    element={<PublicarItem></PublicarItem>}
                ></Route>
                <Route
                    path="/produto/:id"
                    element={<Produto></Produto>}
                ></Route>
                <Route
                    path="/carrinho/:id"
                    element={<Carrinho></Carrinho>}
                ></Route>
                <Route path="/404" element={<div>404 - Not found</div>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
