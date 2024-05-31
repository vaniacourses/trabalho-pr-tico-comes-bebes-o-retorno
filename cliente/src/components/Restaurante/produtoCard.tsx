
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card"
import {Button} from "@/components/ui/button"
import Lanche from "@/assets/produto.jpg"
import { EdicaoProdutoModal } from "./edicaoProdutoModal";

export function Produto() {
    const handleDelete = () => {

    }

    return(

        <Card>  
            <CardHeader>
                <CardTitle className="text-2xl text-center">Hamburguer</CardTitle>
                <CardDescription className="text-base" >Carne e queijo</CardDescription>
            </CardHeader>
            <CardContent>
                <img src={Lanche} alt="Lanche" className="object-cover rounded-md"/>
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
                <EdicaoProdutoModal/>
                <Button className="text-red-600 border-2 border-red-600 hover:bg-red-600 hover:text-white" onClick={handleDelete}>Excluir</Button>
            </CardFooter>
        </Card>
    )
}