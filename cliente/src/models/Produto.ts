

export interface ProdutoResponse{
    id:string
    nome: string,
    descricao: string
    preco: number,
    estoque: number,
    restaurante: {
        id: string
    },
    categoria: {
        id: string
        nome: string
    }
}