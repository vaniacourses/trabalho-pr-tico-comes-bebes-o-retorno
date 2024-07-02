import { HistoricoPedidoCard } from "@/components/Pedido/historicoPedidoCard";

export function HistoricoPedido() {
    return (
        <div className="flex flex-col items-center m-10">
            <h1 className="text-center text-3xl font-bold p-10">Historico de pedidos </h1>
            <div className="w-full flex justify-center">
                <HistoricoPedidoCard/>
            </div>
        </div>
    );
}
