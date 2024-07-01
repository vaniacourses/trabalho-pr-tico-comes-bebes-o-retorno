package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;
@Component
public class FinalizadoState  implements PedidoState{

    @Override
    public void performAction(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não permitida no estado de Finalizado.");
    }

    @Override
    public void cancelAction(Pedido pedido) {
         pedido.setSituacaoPedido(new CanceladoState());
        pedido.setStatus(Status.CANCELADO);
    }
    
    
}
