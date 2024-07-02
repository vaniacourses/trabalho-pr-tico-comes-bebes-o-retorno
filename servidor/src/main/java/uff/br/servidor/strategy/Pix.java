package uff.br.servidor.strategy;

import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.model.Usuario;

public class Pix implements TransacaoStrategy {
    @Override
    public void pagar(Usuario user, PaymentDTO paymentDTO) {
        // TODO: Implementar integração com API de pagamento
        System.out.println("Pagamento realizado com PIX");
    }
}
