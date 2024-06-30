
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card"
import {Button} from "@/components/ui/button"
import RestauranteImg from "@/assets/restauranteImg.png"
import { Link } from "react-router-dom"

interface RestauranteCardProps{

    nome:string
    idUsuario:string
    id:string

}

export function RestauranteCard({nome,idUsuario,id}:RestauranteCardProps) {

    return(
        <Link to={`/restaurante/${id}`}>
            <Card>  
                <CardHeader>
                    <CardTitle className="text-2xl text-center">{nome}</CardTitle>
                </CardHeader>
                <CardContent>
                    <img src={RestauranteImg} alt="imagem restaurante" className="rounded-md"/>
                </CardContent>
            </Card>
        </Link>
    ); 

}