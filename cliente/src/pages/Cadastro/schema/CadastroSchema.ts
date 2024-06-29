import {z} from "zod";




const cpfRegex = new RegExp( /^\d{11}$/);

export const cadastroSchema = z.object({
    nome: z.string().min(3, "O nome deve ter pelo menos 3 caracteres"),
    email: z.string().min(1, { message: "Campo obrigatório" }).email({ message: "E-mail inválido" }),
    telefone: z.string().min(1, { message: "Campo obrigatório" }).regex(/^\d{11}$/, { message: "O telefone deve conter apenas números" }),
    senha_hash: z.string().min(8, { message: "A senha deve ter pelo menos 8 caracteres" }),
    cpf: z.string().min(1, { message: "Campo obrigatório" }).regex(cpfRegex, { message: "CPF inválido" }),
    data_nascimento: z.string().min(1, { message: "Campo obrigatório" }).regex(/^\d{4}-\d{2}-\d{2}$/, { message: "Formato de data inválido" }),
});

export type CadastroSchemaType = z.infer<typeof cadastroSchema>