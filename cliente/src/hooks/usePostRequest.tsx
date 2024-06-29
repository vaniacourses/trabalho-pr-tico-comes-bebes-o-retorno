import { api } from "@/service/api";
import { useEffect } from "react";


export function usePostRequest<T = unknown>(){

    async function postRequest(url:string,data:T,token?:string){
        const headers: Record<string, string> = {
            'Content-Type': 'application/json',
        };
        if(token){
            headers['Authorization'] = token
        }
        await api.post(
            url,
            data,
            headers
        )
    }

    return {postRequest}

}