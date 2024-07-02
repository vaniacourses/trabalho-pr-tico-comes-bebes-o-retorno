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

interface UsuarioInterface{
    id: string;
    nome: string;
    email: string;
    telefone: string;
    senha_hash: string;
    cpf: string;
    status: string;
    role: string;
    data_nascimento: string;
}

interface PedidoInterface {
    id: string;
    usuario: UsuarioInterface;
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
                      'Authorization': `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWNyZXQiLCJleHAiOjE3MTk2ODAzMjUsInN1YiI6ImZhNDE3YjI5LTA5MzgtNDk0ZS1iMjhmLTQzMzQ0M2EwMjc0NiJ9.8y6fReL4w-CKjLUJqbqBWmRglXBYTdyOS43Of2SD_Vo`
                }});
                console.log(response.data);
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
                <CardTitle className="text-2xl text-center">Pedido : {pedido?.id} </CardTitle>
                <CardDescription className="text-base">Status: {pedido?.status}</CardDescription>
            </CardHeader>
            <CardContent>
                <p><strong>ID do Usuário:</strong> {pedido?.usuario.nome}</p>
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
                {/* Coloque qualquer informação adicional no rodapé */}
            </CardFooter>
        </Card>
    )
}
