import { api } from "@/service/api";
import { useEffect, useState } from "react";


export function usePostRequest<T = unknown>(){

    async function postRequest(url:string,data:T,token?:string){
        try {
            const headers: Record<string, string> = {
                'Content-Type': 'application/json',
            };
            if (token) {
                headers['Authorization'] = token;
            }
            
            const response = await api.post(url, data, { headers })
                .then(response => response.data)
                .catch(error => {
                    console.error('Error posting data:', error);
                    throw error; 
                });
            return response;
        } catch (error) {
            console.error('Error in usePostRequest:', error);
        };
    }

    return {postRequest}

}