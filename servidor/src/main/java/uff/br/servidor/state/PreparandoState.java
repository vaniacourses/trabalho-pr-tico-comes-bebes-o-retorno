package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;

@Component
public class PreparandoState implements PedidoState{

    @Override
    public void performAction(Pedido pedido) {
        pedido.setSituacaoPedido(new EntregueState());
        pedido.setStatus(Status.ENTREGUE);
    }

    @Override
    public void cancelAction(Pedido pedido) {
        pedido.setSituacaoPedido(new CanceladoState());
        pedido.setStatus(Status.CANCELADO);
    }
    
}
