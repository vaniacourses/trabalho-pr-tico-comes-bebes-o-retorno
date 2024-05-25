import { GerenciarRestaurante } from "@/pages/GerenciarRestaurante";
import { HomePage } from "@/pages/HomePage";
import { BrowserRouter, Route, Routes } from "react-router-dom";



export function AppRoutes(){


    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/gerenciar-restaurante" element={<GerenciarRestaurante/>}/>
            </Routes>
        </BrowserRouter>
    )

}