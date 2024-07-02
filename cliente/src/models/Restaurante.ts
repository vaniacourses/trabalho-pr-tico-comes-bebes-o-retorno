import { ProdutoResponse } from "./Produto"
import { UsuarioResponse } from "./Usuario"

export interface RestauranteResponse {
    id: string
    nome: string
    cnpj: string
    usuario:UsuarioResponse
    produtos:ProdutoResponse[]
}

export interface RestauranteRequest {
    nome: string
    cnpj: string
    usuario:{
        id:string
    }
}