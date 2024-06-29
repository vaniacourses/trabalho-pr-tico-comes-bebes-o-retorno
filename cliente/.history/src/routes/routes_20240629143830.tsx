import { GerenciarRestaurante } from "@/pages/GerenciarRestaurante";
import { HistoricoPedido } from "@/pages/HistoricoPedido";
import { HomePage } from "@/pages/HomePage";
import { Pedido } from "@/pages/Pedido";
import { LayoutTemplate } from "@/template/Layout";
import { BrowserRouter, Route, Routes } from "react-router-dom";



export function AppRoutes(){


    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LayoutTemplate/>}>
                    <Route path="/home" element={<HomePage/>}/>
                    <Route path="/gerenciar-restaurante" element={<GerenciarRestaurante/>}/>
                    <Route path="/pedido" element={<Pedido/>}/>
                    <Route path="/historico-pedido" element={<HistoricoPedido/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )

}