
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card"

export function PedidoCard(){

    return(

        <Card>  
            <CardHeader>
                <CardTitle className="text-2xl text-center">Pedido</CardTitle>
                <CardDescription className="text-base" >Carne e queijo</CardDescription>
            </CardHeader>
            <CardContent>
                
            </CardContent>
            <CardFooter className="flex flex-row justify">
            
            </CardFooter>
        </Card>
    )
}
