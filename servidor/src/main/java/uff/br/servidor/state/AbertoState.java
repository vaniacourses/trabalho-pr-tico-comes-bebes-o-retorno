package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;

@Component
public class AbertoState implements PedidoState{
    
    @Override
    public void performAction(Pedido pedido) {
        pedido.setSituacaoPedido(new PendenteState());
        pedido.setStatus(Status.PENDENTE);
    }
    
    
    @Override
    public void cancelAction(Pedido pedido) {
        pedido.setSituacaoPedido(new CanceladoState());
        pedido.setStatus(Status.CANCELADO);
    }
}
