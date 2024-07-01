package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;

@Component
public class EntregueState implements PedidoState{ 

    @Override
    public void performAction(Pedido pedido) {
        pedido.setSituacaoPedido(new  FinalizadoState());
        pedido.setStatus(Status.FINALIZADO);
    }

    @Override
    public void cancelAction(Pedido pedido) {
        pedido.setSituacaoPedido(new CanceladoState());
        pedido.setStatus(Status.CANCELADO);
    }
}
