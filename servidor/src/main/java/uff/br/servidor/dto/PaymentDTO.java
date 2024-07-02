package uff.br.servidor.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.TipoTransacao;

@Data
@Builder
@AllArgsConstructor
public class PaymentDTO {
    private float valor;

    private TipoTransacao tipo;
    private String cpf;
    private Pedido pedido;
    private UUID pedidoId;
    
    private String nome;
    private String numeroCartao;
    private String dataVencimento;
    private String cvv;
}
