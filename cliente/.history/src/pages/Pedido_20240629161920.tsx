import { PedidoCard } from "@/components/Pedido/pedidoCard";
import { useParams } from "react-router-dom";

export function Pedido() {
    const { pedidoId } = useParams<{ pedidoId: string }>();
    return (
        <div className="flex flex-col items-center m-10">
            <h1 className="text-center text-3xl font-bold p-10">Pedido</h1>
            <div className="w-full flex justify-center">
                <PedidoCard pedidoId={pedidoId}/>
            </div>
        </div>
    );
}
