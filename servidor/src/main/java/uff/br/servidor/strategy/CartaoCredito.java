package uff.br.servidor.strategy;

import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.model.Usuario;

public class CartaoCredito implements TransacaoStrategy {
    @Override
    public void pagar(Usuario user, PaymentDTO paymentDTO) {
        String nome = paymentDTO.getNome();
        String numeroCartao = paymentDTO.getNumeroCartao();
        String dataVencimento = paymentDTO.getDataVencimento();
        String cvv = paymentDTO.getCvv();
        // TODO: Implementar integração com API de pagamento
        System.out.println("Pagamento realizado com cartão de crédito");
    }
}
