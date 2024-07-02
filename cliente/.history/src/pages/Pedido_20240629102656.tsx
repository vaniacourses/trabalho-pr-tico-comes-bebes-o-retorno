import { PedidoCard } from "@/components/Pedido/pedidoCard";


export function Pedido() {
    return (
        <div className="flex  flex-col m-10">

            <h1 className="text-center text-3xl font-bold p-10" >Pedido </h1>
            {/* MAP OF PRODUCTS */}
            <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
                <PedidoCard/>
            </div>
        </div>
    )
}