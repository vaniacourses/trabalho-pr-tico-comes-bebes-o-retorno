

export interface UsuarioRequest {
    nome:String,
    email:String;
    telefone:String;
    senha:String;
    cpf:String;
    data_nascimento:String;
}

export interface UsuarioResponse {
    id:string;
    nome:String,
    email:String;
    telefone:String;
    senha:String;
    role:Role;
    status:Status;
    cpf:String;
    data_nascimento:String;
}

export interface UsuarioLogin{
    cpf:string;
    senha:string;
}

export enum Role {
    USER = "USER",
    DONO_DE_RESTAURANTE = "DONO_DE_RESTAURANTE",
    ENTREGADOR = "ENTREGADOR",
    ADMIN = "ADMIN"
}
export enum Status{
    ATIVO = "ATIVO",
    INATIVO = "INATIVO"
}