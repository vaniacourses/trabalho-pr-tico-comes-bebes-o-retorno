import { LoadingCircle } from "@/components/Loading"
import { useFetch } from "@/hooks/useFetch"
import { RestauranteResponse } from "@/models/Restaurante"
import { RestauranteCard } from "./RestauranteCard"


export function RestauranteListPage(){

    const {data,isFetching,error} = useFetch<RestauranteResponse[]>("/restaurantes")

    return(
        <section className="m-10">
            {
                data ? (
                    <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
                        {data.map((restaurante: RestauranteResponse) => (
                            <RestauranteCard
                                key={restaurante.usuario.id}
                                nome={restaurante.nome}
                                id={restaurante.id}
                                idUsuario={restaurante.usuario.id}
                            />
                            ))}
                    </div>
                ):(
                    
                    <LoadingCircle/>
                    
                )
            }
        </section>
    )

}