import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import Lanche from "@/assets/produto.jpg";
import axios from "axios";

interface ProdutoCardProps {
    id: string;
    nome: string;
    descricao: string;
    preco: number;
}

export function ProdutoCard({ nome, descricao, id, preco }: ProdutoCardProps) {
    const handleCarrinho = async () => {
        console.log(id);
        try {
            const token = localStorage.getItem('acess-token');
            console.log(token);
            const response = await axios.post(
                'http://localhost:8080/pedido/carrinho/'+id,
                {
                    headers: {
                        'Authorization': token
                    }
                }
            );
            console.log("Produto adicionado ao carrinho:", response.data);
        } catch (error) {
            console.error("Erro ao adicionar produto ao carrinho:", error);
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
                <Button className="text-green-600 bg-white border-2 border-green-600 hover:bg-green-600 hover:text-white" onClick={handleCarrinho}>Adicionar ao Carrinho</Button>
            </CardFooter>
        </Card>
    );
}
