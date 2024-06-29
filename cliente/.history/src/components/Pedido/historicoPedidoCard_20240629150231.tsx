import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card";
import { Link } from 'react-router-dom';

interface PedidoCardProps {
    pedidoId: string;
}

interface PedidoInterface {
    id: string;
    status: string;
}

export function HistoricoPedidoCard({ pedidoId } : PedidoCardProps){
    const [pedido, setPedido] = useState<PedidoInterface | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchPedido = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/pedido/id/${pedidoId}`,{
                    headers: {
                      'Authorization': `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWNyZXQiLCJleHAiOjE3MTk2Nzk1NzAsInN1YiI6ImZhNDE3YjI5LTA5MzgtNDk0ZS1iMjhmLTQzMzQ0M2EwMjc0NiJ9.1kYYNR26jNFbgLQlX2swsYX-aAG6J5uPuyPe0BJYHvM`
                }});
                setPedido(response.data);
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
    }, [pedidoId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return(
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
    );
}