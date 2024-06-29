

export interface Usuario {
    nome:String,
    email:String;
    telefone:String;
    senha_hash:String;
    cpf:String;
    data_nascimento:String;
}

export interface UsuarioLogin{
    cpf:string;
    senha_hash:string;
}

export enum Role {
    USER = "USER",
    DONO_DE_RESTAURANTE = "DONO_DE_RESTAURANTE",
    ENTREGADOR = "ENTREGADOR",
    ADMIN = "ADMIN"
}