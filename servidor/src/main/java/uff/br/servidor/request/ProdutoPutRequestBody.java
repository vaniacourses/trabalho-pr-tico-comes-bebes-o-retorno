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
public class ProdutoPutRequestBody {
    private UUID id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
}
