import { HistoricoPedidoCard } from "@/components/Pedido/historicoPedidoCard";
import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface PedidoInterface {
    id: string;
    status: string;
}

export function HistoricoPedido() {
    const [pedidos, setPedidos] = useState<PedidoInterface[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    useEffect(() => {
        const fetchPedido = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/pedido/21332109810`,{
                    headers: {
                      'Authorization': `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWNyZXQiLCJleHAiOjE3MTk2OTEzOTQsInN1YiI6ImZhNDE3YjI5LTA5MzgtNDk0ZS1iMjhmLTQzMzQ0M2EwMjc0NiJ9.ODBr0c2dNx6tGyp1Y4jr5Nbhocx3_4HhqZpM5_HHQbA`
                }});
                console.log(response.data);
                setPedidos(response.data);
                setLoading(false);
            } catch (err: unknown) {
                if (axios.isAxiosError(err)) {
                    setError(err.message);
                } else if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError('Um erro desconhecido ocorreu');
                }
                setLoading(false);
            }
        };

        fetchPedido();
    }, );

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }


    return (
        <div className="flex flex-col items-center m-10">
            <h1 className="text-center text-3xl font-bold p-10">Historico de pedidos </h1>
            <div className="w-full flex justify-center">
                {pedidos.map(pedido => (
                    <HistoricoPedidoCard pedidoId={pedido.id}/>
                ))}
            </div>
        </div>
    );
}
