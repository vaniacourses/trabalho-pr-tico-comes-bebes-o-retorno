import { LoadingCircle } from "@/components/Loading";
import { useFetch } from "@/hooks/useFetch";
import { ProdutoResponse } from "@/models/Produto";
import { RestauranteResponse } from "@/models/Restaurante";
import { useParams } from "react-router-dom";
import { ProdutoCard } from "./ProdutoCard";
import { useEffect } from "react";


export function RestaurantePage(){
    const { restauranteId } = useParams();


    useEffect(()=>{
        console.log(restauranteId)
    },[restauranteId])

    const {data,isFetching,error} = useFetch<RestauranteResponse>(`/restaurantes/${restauranteId}`)

    return(
        <section>
        {
            data ? (
                <div>
                    <h1 className="text-3xl font-bold uppercase p-5 text-center ">{data.nome}</h1>
                    <h2 className="text-2xl text-center text-gray-600">Nossos produtos</h2>
                    <div className="grid m-10 grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
                        {
                            data.produtos.map((produto: ProdutoResponse) => (
                                <ProdutoCard
                                    key={produto.id}
                                    nome={produto.nome}
                                    id={produto.id}
                                    preco={produto.preco}
                                    descricao={produto.descricao}
                                />
                            ))
                            
                        }
                    </div>
                </div>
            ):(
                <LoadingCircle/>
            )
        }
        </section>
    );

}