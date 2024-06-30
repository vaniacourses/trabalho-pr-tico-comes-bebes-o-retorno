import { api } from "@/service/api"
import { useEffect, useState } from "react"

export function useGetRequest<T = unknown>(url: string,token?:string) {
    const [data,setData] = useState<T>()
    const [error,setError] = useState<unknown>()
    const [isFetching,setIsFetching] = useState<boolean>(true)

    useEffect(()=>{
        const headers: Record<string, string> = {
            'Content-Type': 'application/json',
        };
        if (token) {
            headers['Authorization'] = token;
        }
        api.get(url,{headers}).then((response)=>{
            setData(response.data)
        }).catch(error=>{
            setError(error)
        }).finally(()=>{
            setIsFetching(false)
        })
    },[url])

    return {data,error,isFetching}
}  