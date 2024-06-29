import { CadastroPage } from "@/pages/Cadastro/CadastroPage";
import { GerenciarRestaurante } from "@/pages/GerenciarRestaurante";
import { HistoricoPedido } from "@/pages/HistoricoPedido";
import { HomePage } from "@/pages/HomePage";
import { Pedido } from "@/pages/Pedido";
import { LoginPage } from "@/pages/Login/LoginPage";
import { LayoutTemplate } from "@/template/Layout";
import { BrowserRouter, Route, Routes } from "react-router-dom";



export function AppRoutes(){


    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LayoutTemplate/>}>
                    <Route path="/cadastro" element={<CadastroPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/home" element={<HomePage/>}/>
                    <Route path="/gerenciar-restaurante" element={<GerenciarRestaurante/>}/>
                    <Route path="/historico-pedido" element={<HistoricoPedido/>}/>
                    <Route path="/pedido/:pedidoId" element={<Pedido/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )

}