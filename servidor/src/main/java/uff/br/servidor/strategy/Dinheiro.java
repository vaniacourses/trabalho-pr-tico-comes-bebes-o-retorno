package uff.br.servidor.strategy;

import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.model.Usuario;

public class Dinheiro implements TransacaoStrategy {
    @Override
    public void pagar(Usuario user, PaymentDTO paymentDTO) {
        System.out.println("Pagamento será realizado na entrega em dinheiro");
    }
}
