import { CadastroPage } from "@/pages/Cadastro/CadastroPage";
import { GerenciarRestaurante } from "@/pages/GerenciarRestaurante";
import { HistoricoPedido } from "@/pages/HistoricoPedido";
import { HomePage } from "@/pages/HomePage";
import { Pedido } from "@/pages/Pedido";
import { LoginPage } from "@/pages/Login/LoginPage";
import { LayoutTemplate } from "@/template/Layout";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "@/context/AuthContext";



export function AppRoutes(){


    const { isAuthenticated, isLoading } = useContext(AuthContext)

    if (isLoading) return <></>

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LayoutTemplate/>}>
                    {
                        !isAuthenticated &&
                            <Route>
                                <Route path="/cadastro" element={<CadastroPage/>}/>
                                <Route path="/login" element={<LoginPage/>}/>
                            </Route>
                    }
                    <Route path="/home" element={<HomePage/>}/>
                    <Route path="/gerenciar-restaurante" element={<GerenciarRestaurante/>}/>
                    <Route path="/historico-pedido" element={<HistoricoPedido/>}/>
                    <Route path="/pedido/:pedidoId" element={<Pedido/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )

}