import React from 'react';
import { useParams } from 'react-router-dom';
import { PedidoCard } from "@/components/Pedido/pedidoCard";

export function Pedido() {
    const { pedidoId } = useParams<{ pedidoId: string }>();

    if (!pedidoId) {
        return <div>Pedido ID não encontrado</div>;
    }

    return (
        <div className="flex flex-col items-center m-10">
            <h1 className="text-center text-3xl font-bold p-10">Pedido</h1>
            <div className="w-full flex justify-center">
                <PedidoCard pedidoId={pedidoId} />
            </div>
        </div>
    );
}
