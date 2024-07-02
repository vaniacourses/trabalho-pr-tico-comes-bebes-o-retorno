package uff.br.servidor.strategy;

import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.model.Transacao;
import uff.br.servidor.model.Usuario;

public interface TransacaoStrategy {
    public void pagar(Usuario user, PaymentDTO paymentDTO);
}
