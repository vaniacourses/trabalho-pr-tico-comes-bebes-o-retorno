package uff.br.servidor.state;

import org.springframework.stereotype.Component;

import uff.br.servidor.model.Pedido;
@Component
public class CanceladoState implements PedidoState{
    
    @Override
    public void performAction(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não permitida no estado de Cancelado.");
    }

    @Override
    public void cancelAction(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não permitida no estado de Cancelado.");
    }
}
