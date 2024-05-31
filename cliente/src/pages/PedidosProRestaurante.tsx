import { Button } from "@/components/ui/button"
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card"

export function PedidosProRestaurante() {


    const getStatusClass = (status:String) => {
        switch (status) {
          case 'Pendente':
            return 'text-orange-500';
          case 'Preparando':
            return 'text-yellow-500';
          case 'Saiu pra entrega':
            return 'text-blue-500';
          case 'Entregue':
            return 'text-green-500';
          default:
            return 'text-gray-500';
        }
      };

    return (
        <div className="flex flex-col justify-center items-center">
            <h1 className="text-3xl font-bold p-10" >PEDIDOS DOS USUARIOS</h1>
            <Card className="border-2  rounded-lg shadow-lg p-4">
                <CardHeader className="p-4 border-b">
                    <CardTitle className="text-2xl text-center">Pedido nº 67890</CardTitle>
                    <CardDescription className="text-2xl">Cliente: <b>Maria</b></CardDescription>
                </CardHeader>
                <CardContent className="p-4">
                    <h2 className="text-xl font-bold uppercase" >Itens</h2>
                    <ul className="space-y-1 list-disc list-inside ml-4">
                        <li className="italic font-medium text-base" >1x Lasanha</li>
                        <li className="italic font-medium text-base" >1x Coca-Cola</li>
                    </ul>
                    <p className="mt-2 text-2xl"><strong>Total:</strong> R$35,00</p>
                    <p className="mt-2 text-xl">Pendente</p>
                </CardContent>
                <CardFooter className="p-4 flex flex-row gap-5 border-t">
                    <Button className="text-red-600 border-2 border-red-600 hover:bg-red-600 hover:text-white">Aceitar Pedido</Button>
                    <Button className="text-red-600 border-2 border-red-600 hover:bg-red-600 hover:text-white">Cancelar</Button>
                    <Button className="text-blue-600 border-2 border-blue-600 hover:bg-blue-600 hover:text-white">Mais Detalhes</Button>
                </CardFooter>
            </Card>
        </div> 
    )
}