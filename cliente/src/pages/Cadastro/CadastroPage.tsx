import {  useForm } from "react-hook-form"
import { cadastroSchema, CadastroSchemaType } from "./schema/CadastroSchema"
import { zodResolver } from "@hookform/resolvers/zod"
import { z } from "zod"
import { Button } from "@/components/ui/button"
import { FormField } from "@/components/FormField"
import { usePostRequest } from "@/hooks/usePostRequest"
import { Usuario } from "@/models/Usuario"
import { useNavigate } from "react-router-dom"


export function CadastroPage(){

    const {
        register,
        handleSubmit,
        formState: { errors },
      }= useForm<CadastroSchemaType>({
        resolver: zodResolver(cadastroSchema)
    });

    const {postRequest} = usePostRequest<Usuario>();
    const navigate = useNavigate()

    function onSubmit(values: CadastroSchemaType) {
        postRequest("/usuario",values)
        navigate("/login")
    }     
    
    return (
        <div className="flex flex-col justify-center items-center py-5">
            
                <form onSubmit={handleSubmit(onSubmit)} className="space-y-3 bg-gray-50 p-10 rounded-lg shadow dark:border ">
                    <h1 className="text-2xl font-bold uppercase p-5">Crie sua conta</h1>
                    <FormField
                        label="Nome completo"
                        placeholder="Nome"
                        errorMessage={errors.nome?.message}
                        {...register("nome")}
                    />
                    <FormField
                        label="CPF"
                        placeholder="CPF"
                        type="number"
                        errorMessage={errors.cpf?.message}
                        {...register("cpf")}
                    />
                    <FormField
                        label="Email"
                        placeholder="Email"
                        errorMessage={errors.email?.message}
                        {...register("email")}
                    />
                     <FormField
                        label="Senha"
                        placeholder="senha"
                        type="password"
                        errorMessage={errors.senha_hash?.message}
                        {...register("senha_hash")}
                    />
                     <FormField
                        label="Telefone"
                        type="number"
                        placeholder="telefone"
                        errorMessage={errors.telefone?.message}
                        {...register("telefone")}
                    />
                     <FormField
                        label="Data de nascimento"
                        type="date"
                        placeholder="data_nascimento"
                        errorMessage={errors.data_nascimento?.message}
                        {...register("data_nascimento")}
                    />
                    <Button type="submit">Submit</Button>
                </form>
                

        </div>  
    )
}