package uff.br.servidor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnoreProperties(value = {"nome","email","telefone","senha_hash","cpf","status","role","data_nascimento"},allowGetters = false)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name="itens",insertable = false, updatable = false)
    private List<ProdutoPedido> itens;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;
}
