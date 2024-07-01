import { useForm } from "react-hook-form";
import { loginSchema, LoginSchemaType } from "./schema/LoginSchema";
import { zodResolver } from "@hookform/resolvers/zod";
import { usePostRequest } from "@/hooks/usePostRequest";
import { UsuarioLogin } from "@/models/Usuario";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { FormField } from "@/components/FormField";
import { useAuthContext } from "@/hooks/useAuthContext";

export function LoginPage() {

    const {postRequest} = usePostRequest<UsuarioLogin>();
    const {setIsAuthenticated, setUserRole} = useAuthContext();
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<LoginSchemaType>({
        resolver: zodResolver(loginSchema)
    });

    async function onSubmit(values: LoginSchemaType) {
        const data = await postRequest("/auth/login", values);
        localStorage.setItem("acess-token", data.token);
        sessionStorage.setItem("cpf", values.cpf); // Salva o CPF no session storage
        setUserRole(data.role);
        setIsAuthenticated(true);
        navigate("/");
    }

    return (
        <div className="flex flex-col justify-center items-center py-5">
            <form onSubmit={handleSubmit(onSubmit)} className="space-y-3 bg-gray-50 p-10 rounded-lg shadow dark:border">
                <h1 className="text-2xl font-bold uppercase p-5">Login</h1>
                <FormField
                    label="CPF"
                    placeholder="CPF"
                    type="number"
                    errorMessage={errors.cpf?.message}
                    {...register("cpf")}
                />
                <FormField
                    label="Senha"
                    placeholder="senha"
                    type="password"
                    errorMessage={errors.senha_hash?.message}
                    {...register("senha_hash")}
                />
                <Button type="submit">Logar</Button>

                <p className="text-center text-base text-black">Não tem uma conta? <Link className="font-bold text-gray-500" to={"/cadastro"}> Cadastre-se aqui</Link></p>
            </form>
        </div>
    );
}
