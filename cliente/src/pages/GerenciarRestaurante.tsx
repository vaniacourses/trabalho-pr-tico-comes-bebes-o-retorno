import { CriarProdutoModal } from "@/components/Restaurante/criarProdutoModal";
import { Produto } from "@/components/Restaurante/produtoCard";
import { Button } from "@/components/ui/button";
import { useAuthContext } from "@/hooks/useAuthContext";
import { useEffect, useState } from "react";


export function GerenciarRestaurante() {

    const {token} = useAuthContext();
    
    useEffect(()=>{

    },[token])

    return (
        <div className="flex  flex-col m-10">

            <h1 className="text-center  text-3xl font-bold p-10" >Gerencie seu Restaurante</h1>
            {/* <div className="flex self-start p-5"> */}
                <CriarProdutoModal/>
            {/* </div> */}
            {/* MAP OF PRODUCTS */}
            <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
                <Produto/>
                <Produto/>
                <Produto/>
                <Produto/>
            </div>
        </div>
    )
}