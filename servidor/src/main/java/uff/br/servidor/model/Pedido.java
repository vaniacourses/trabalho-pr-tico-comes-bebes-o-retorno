package uff.br.servidor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uff.br.servidor.state.AbertoState;
import uff.br.servidor.state.CanceladoState;
import uff.br.servidor.state.EntregueState;
import uff.br.servidor.state.FinalizadoState;
import uff.br.servidor.state.PedidoState;
import uff.br.servidor.state.PendenteState;
import uff.br.servidor.state.PreparandoState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Transient
    private PedidoState situacaoPedido;


    public static PedidoState obterEstado(Status status) {
        Map<Status, PedidoState> estadoMap = new HashMap<>();
        estadoMap.put(Status.ABERTO, new AbertoState());
        estadoMap.put(Status.PENDENTE, new PendenteState());
        estadoMap.put(Status.PREPARANDO, new PreparandoState());
        estadoMap.put(Status.FINALIZADO, new FinalizadoState());
        estadoMap.put(Status.ENTREGUE, new EntregueState());
        estadoMap.put(Status.CANCELADO, new CanceladoState());

        return estadoMap.get(status);
    }

    public void inicializarSituacaoPedido() {
        this.situacaoPedido = Pedido.obterEstado(this.status);
    }

    public void cancelAction() {
        situacaoPedido.cancelAction(this);
    }
    public void performAction() {
        situacaoPedido.performAction(this);
    }
}
