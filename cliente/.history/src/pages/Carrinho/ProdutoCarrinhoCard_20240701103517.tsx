import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import Lanche from "@/assets/produto.jpg";
import axios from "axios";
import { useState } from "react";

interface ProdutoCardProps {
    id_item: string;
    id: string;
    nome: string;
    descricao: string;
    preco: number;
}

export function ProdutoCarrinhoCard({ id_item, nome, descricao, id, preco }: ProdutoCardProps) {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const token = localStorage.getItem('acess-token');

    const handleExcluirCarrinho = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await axios.delete(`http://localhost:8080/pedido/carrinho/${id_item}`, {
                headers: {
                    'Authorization': token
                }
            });
            console.log("Produto removido do carrinho:", response.data);
        } catch (err: unknown) {
            if (axios.isAxiosError(err)) {
                setError(err.message);
            } else if (err instanceof Error) {
                setError(err.message);
            } else {
                setError('Um erro desconhecido ocorreu');
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <Card>
            <CardHeader>
                <CardTitle className="text-2xl text-center">{nome}</CardTitle>
                <CardDescription className="text-base">{descricao}</CardDescription>
            </CardHeader>
            <CardContent>
                <img src={Lanche} alt="Lanche" className="object-cover rounded-md" />
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
                <p className="text-2xl">Preço: R$ <b>{preco}</b></p>
                <Button
                    className="text-green-600 bg-white border-2 border-green-600 hover:bg-green-600 hover:text-white"
                    onClick={handleExcluirCarrinho}
                    disabled={loading}
                >
                    {loading ? "Excluindo..." : "Excluir do Carrinho"}
                </Button>
            </CardFooter>
            {error && <p className="text-red-500 text-center mt-2">{error}</p>}
        </Card>
    );
}
