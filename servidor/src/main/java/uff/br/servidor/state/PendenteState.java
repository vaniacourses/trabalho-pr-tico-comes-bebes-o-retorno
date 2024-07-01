package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;

@Component
public class PendenteState implements PedidoState {
    

    @Override
    public void performAction(Pedido pedido) {
        pedido.setSituacaoPedido(new PreparandoState());
        pedido.setStatus(Status.PREPARANDO);
    }

    @Override
    public void cancelAction(Pedido pedido) {
         pedido.setSituacaoPedido(new CanceladoState());
        pedido.setStatus(Status.CANCELADO);
    }
}
