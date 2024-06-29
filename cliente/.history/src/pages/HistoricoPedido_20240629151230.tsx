import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card";
import { Link } from "react-router-dom";

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
                      'Authorization': `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWNyZXQiLCJleHAiOjE3MTk2OTE2MjMsInN1YiI6ImZhNDE3YjI5LTA5MzgtNDk0ZS1iMjhmLTQzMzQ0M2EwMjc0NiJ9.qVFsBRMhgROciz7nDJpW9mqgk6vk5tqZ0PjYXTZkU40`
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
                    <Card>
                    <CardHeader>
                        <CardTitle className="text-2xl text-center">
                            <Link to={`/pedido/${pedido?.id}`}>Pedido : {pedido?.id} </Link>
                        </CardTitle>
                        <CardDescription className="text-base">Status: {pedido?.status}</CardDescription>
                    </CardHeader>
                    <CardContent>
                    </CardContent>
                    <CardFooter className="flex flex-row justify-between">
                        {/* Coloque qualquer informação adicional no rodapé */}
                    </CardFooter>
                </Card>
                ))}
            </div>
        </div>
    );
}
