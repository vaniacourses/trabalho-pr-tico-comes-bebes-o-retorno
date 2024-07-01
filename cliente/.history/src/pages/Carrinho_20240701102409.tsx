import { LoadingCircle } from "@/components/Loading";
import { ProdutoResponse } from "@/models/Produto";
import { useEffect, useState } from "react";
import axios from "axios";
import { ProdutoCarrinhoCard } from "./Carrinho/ProdutoCarrinhoCard";

interface ProdutoPedido {
    id: string;
    quantidade: number;
    produto: ProdutoResponse;
}

interface Carrinho {
    id: string;
    itens: ProdutoPedido[];
}

export function Carrinho() {
    const [carrinho, setCarrinho] = useState<Carrinho>();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const token = localStorage.getItem('acess-token');

    useEffect(() => {
        const fetchCarrinho = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/pedido/carrinho`, {
                    headers: {
                        'Authorization': token
                    }
                });
                setCarrinho(response.data);
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

        fetchCarrinho();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <section>
            {carrinho ? (
                <div>
                    <h2 className="text-2xl text-center text-gray-600">Meu carrinho</h2>
                    <div className="grid m-10 grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
                        {carrinho.itens.map((item: ProdutoPedido) => (
                            <ProdutoCarrinhoCard
                                key={item.produto.id}
                                id_item={item.id}
                                nome={item.produto.nome}
                                id={item.produto.id}
                                preco={item.produto.preco}
                                descricao={item.produto.descricao}
                            />
                        ))}
                    </div>
                </div>
            ) : (
                <LoadingCircle />
            )}
        </section>
    );
}
