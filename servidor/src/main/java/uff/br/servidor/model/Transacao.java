package uff.br.servidor.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "transacao")
@DynamicUpdate
@DynamicInsert
public class Transacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    private TipoTransacao tipo;

    @NotNull(message = "O CPF não pode ser nulo")
    @Length(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    @CPF(message = "CPF inválido") 
    private String cpf;

    @OneToOne
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    @Column(name="pedido_id")
    private UUID pedidoId;
}
