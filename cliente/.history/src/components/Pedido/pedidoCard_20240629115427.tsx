import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card";

interface PedidoCardProps {
    pedidoId: string;
}

interface PedidoInterface {
    id: string;
    usuario: string;
    status: string;
}

export function PedidoCard({ pedidoId } : PedidoCardProps){
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
                <CardTitle className="text-2xl text-center">Pedido</CardTitle>
                <CardDescription className="text-base">Detalhes do Pedido</CardDescription>
            </CardHeader>
            <CardContent>
                <p><strong>ID do Pedido:</strong> {pedido?.id}</p>
                <p><strong>Status:</strong> {pedido?.status}</p>
                <p><strong>ID do Usuário:</strong> {pedido?.usuario}</p>
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
                {/* Coloque qualquer informação adicional no rodapé */}
            </CardFooter>
        </Card>
    )
}
