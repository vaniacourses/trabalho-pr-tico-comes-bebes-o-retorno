package uff.br.servidor.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uff.br.servidor.model.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponseDTO {
    
    private String token;
    private Role role;

}
