package uff.br.servidor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostRequestBody {
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;
    private String restaurante;
    private String categoria;

}
