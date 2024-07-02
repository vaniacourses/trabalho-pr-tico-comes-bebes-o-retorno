package uff.br.servidor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPedidoPutRequestBody {
    private UUID id;
    private UUID produto_id;
    private int quantidade;
    private UUID pedido_id;
}
