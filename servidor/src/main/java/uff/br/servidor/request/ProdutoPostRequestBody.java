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
public class ProdutoPostRequestBody {
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;
    private UUID restaurante_id;
    private String categoria;

}
