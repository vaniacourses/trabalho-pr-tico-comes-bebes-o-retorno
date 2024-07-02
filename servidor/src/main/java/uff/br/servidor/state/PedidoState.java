package uff.br.servidor.state;

import uff.br.servidor.model.Pedido;

public interface PedidoState {
    
    void performAction(Pedido pedido);
    void cancelAction(Pedido pedido);
    
}
