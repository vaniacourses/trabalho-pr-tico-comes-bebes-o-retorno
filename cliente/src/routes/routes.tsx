import { CadastroPage } from "@/pages/Cadastro/CadastroPage";
import { GerenciarRestaurante } from "@/pages/GerenciarRestaurante";
import { HomePage } from "@/pages/HomePage";
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
                </Route>
            </Routes>
        </BrowserRouter>
    )

}