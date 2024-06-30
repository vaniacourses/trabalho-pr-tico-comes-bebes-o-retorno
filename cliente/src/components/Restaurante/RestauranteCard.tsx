import React from "react";
import { Link } from "react-router-dom";

import {
  Card,
  CardHeader,
  CardFooter,
  CardTitle,
  CardDescription,
  CardContent,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";

interface RestauranteCardProps {
  restaurante: {
    id: string;
    nome: string;
    cnpj: string;
  };
  onDelete?: () => void;
}

const RestauranteCard: React.FC<RestauranteCardProps> = ({
  restaurante,
  onDelete,
}) => {
  const handleDelete = () => {
    if (onDelete) {
      onDelete();
    }
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-2xl text-center">
          {restaurante.nome}
        </CardTitle>
        <CardDescription className="text-base">{`CNPJ: ${restaurante.cnpj}`}</CardDescription>
      </CardHeader>
      <CardContent>
        {/* Conteúdo adicional do card pode ser adicionado aqui */}
        {/*não tem imagem por enquanto */}
      </CardContent>
      <CardFooter className="flex flex-row justify-between">
        <Link to={`/restaurante/${restaurante.id}`}>
          <Button className="bg-blue-600 hover:bg-blue-700 text-white">
            Detalhes
          </Button>
        </Link>
        {onDelete && (
          <Button
            className="text-red-600 border-2 border-red-600 hover:bg-red-600 hover:text-white"
            onClick={handleDelete}
          >
            Excluir
          </Button>
        )}
      </CardFooter>
    </Card>
  );
};

export default RestauranteCard;
