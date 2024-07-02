package uff.br.servidor.strategy;

import org.springframework.beans.factory.annotation.Autowired;

import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.model.Carteira;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.service.CarteiraService;

public class Saldo implements TransacaoStrategy {
    @Autowired
    private CarteiraService carteiraService;

    @Override
    public void pagar(Usuario user, PaymentDTO paymentDTO) {
        Carteira carteira = carteiraService.getById(user.getId());
        carteira.setSaldo(carteira.getSaldo() - paymentDTO.getValor());
        carteiraService.update(carteira.getUsuarioId(), carteira);
    }
}
