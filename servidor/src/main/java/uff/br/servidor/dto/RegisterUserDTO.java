package uff.br.servidor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Date data_nascimento;
    private String telefone;
}
