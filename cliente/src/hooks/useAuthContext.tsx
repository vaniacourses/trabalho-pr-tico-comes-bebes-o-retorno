import { AuthContext } from "@/context/AuthContext";
import { useContext } from "react";



export function useAuthContext(){

    const context = useContext(AuthContext);
    if(context == undefined){
        throw new Error(
            "ERROR CONTEXT"
        )
    }
    return context;

}