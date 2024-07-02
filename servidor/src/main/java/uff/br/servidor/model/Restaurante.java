package uff.br.servidor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "restaurante")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@DynamicUpdate
public class Restaurante {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cnpj")
    private String cnpj;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "produtos", insertable = false, updatable = false)
    private List<Produto> produtos;

}
