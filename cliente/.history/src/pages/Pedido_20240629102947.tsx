import { PedidoCard } from "@/components/Pedido/pedidoCard";

export function Pedido() {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 m-10">
            <h1 className="text-center text-3xl font-bold p-10">Pedido</h1>
            <div className="flex justify-center">
                <PedidoCard />
            </div>
        </div>
    );
}
