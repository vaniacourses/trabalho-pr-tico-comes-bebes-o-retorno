package uff.br.servidor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nome")
    private String nome;

    @Email(message = "Email invalido")
    @Column(name = "email")
    private String email;

    @Length(min = 9, max = 15, message = "O CPF deve ter 11 caracteres")
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "senha_hash")
    private String senha_hash;

    @Length(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    @CPF(message = "CPF invalido")
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "status")
    private UsuarioStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "data_nascimento")
    private Date data_nascimento;

}
