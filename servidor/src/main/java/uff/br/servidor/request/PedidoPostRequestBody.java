package uff.br.servidor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uff.br.servidor.model.Status;
import uff.br.servidor.state.PedidoState;

import java.util.UUID;

import jakarta.persistence.Transient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPostRequestBody {
    private UUID usuario;
    private Status status;

    @Transient
    private PedidoState situacaoPedido;
}
