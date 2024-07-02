import { z } from "zod";

const cpfRegex = new RegExp( /^\d{11}$/);

export const loginSchema = z.object({
  cpf: z.string().min(1, { message: "Campo obrigatório" }).regex(cpfRegex, { message: "CPF inválido" }),
      senha: z
      .string()
      .min(1, { message: "Campo obrigatório" }),
  });
  
export type LoginSchemaType = z.infer<typeof loginSchema>;