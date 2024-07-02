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
    status: string;
}

interface RestauranteInterface{
    id: string;
    nome: string;
}

interface ProdutoInterface{
    id: string;
    nome: string;
    descricao: string;
    preco: number;
    restaurante: RestauranteInterface;
}

interface ProdutoPedidoInterface{
    id: string;
    quantidade: number;
    produto: ProdutoInterface;
}


export function PedidoCard({ pedidoId } : PedidoCardProps){
    const [pedido, setPedido] = useState<PedidoInterface | null>(null);
    const [produtoPedido, setProdutoPedido] = useState<ProdutoPedidoInterface[]>([]);
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
                const response_produto_pedido = await axios.get(`http://localhost:8080/produto_pedido/${pedidoId}`,{
                    headers: {
                      'Authorization': `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWNyZXQiLCJleHAiOjE3MTk2Nzk1NzAsInN1YiI6ImZhNDE3YjI5LTA5MzgtNDk0ZS1iMjhmLTQzMzQ0M2EwMjc0NiJ9.1kYYNR26jNFbgLQlX2swsYX-aAG6J5uPuyPe0BJYHvM`
                }});
                setProdutoPedido(response_produto_pedido.data);
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
                <p><strong>ID do Usuário:</strong> </p>
                <div>
                    <h2 className="text-xl font-bold mt-4">Produtos</h2>
                    {produtoPedido.map(produtoPed => (
                        <div key={produtoPed.id} className="mt-2">
                            <p><strong>Nome Produto:</strong> {produtoPed.produto.nome}</p>
                            <p><strong>Descrição:</strong> {produtoPed.produto.descricao}</p>
                            <p><strong>Preço:</strong> R$ {produtoPed.produto.preco * produtoPed.quantidade}</p>
                        </div>
                    ))}
                </div>
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
                {/* Coloque qualquer informação adicional no rodapé */}
            </CardFooter>
        </Card>
    )
}
