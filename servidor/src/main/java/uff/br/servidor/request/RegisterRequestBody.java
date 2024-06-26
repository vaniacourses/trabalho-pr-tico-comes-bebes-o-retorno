
package uff.br.servidor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uff.br.servidor.model.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestBody {
    private String nome;
    private String email;
    private String cpf;
    private Role role;
    private String senha;
}
