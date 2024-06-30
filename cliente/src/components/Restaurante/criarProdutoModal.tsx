
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
    DialogFooter
  } from "@/components/ui/dialog"
import { Button } from "../ui/button";
import { Input } from "@/components/ui/input"
import { Textarea } from "../ui/textarea";
import { useEffect } from "react";
import { useGetRequest } from "@/hooks/useGetRequest";



export function CriarProdutoModal(){


    const {data,isFetching,error} = useGetRequest('/categoria',)
    
    useEffect(()=>{

    },[])

    return(
        <Dialog>

            <DialogTrigger>
                    <Button
                        className="text-sky-700 border-2 bg-blue-100  border-sky-700 hover:bg-sky-700 hover:text-white"
                    >
                        Adicionar Produto
                    </Button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Adição do Produto</DialogTitle>
                </DialogHeader>
                <div className="grid grid-cols-4 items-center gap-4">
                    <label htmlFor="name" className="text-right">
                        Nome
                    </label>
                    <Input
                        id="name"
                        className="col-span-3"
                    />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                    <label htmlFor="description" className="text-right">
                        Descrição
                    </label>
                    <Textarea id="description" />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                    <label htmlFor="price" className="text-right">
                        Preço
                    </label>
                    <Input
                        id="price"
                        className="col-span-3"
                        type="number"
                    />
                </div>          
                <div className="grid grid-cols-4 items-center gap-4">
                    <label htmlFor="estoque" className="text-right">
                        Estoque
                    </label>
                    <Input
                        id="estoque"
                        className="col-span-3"
                        type="number"
                    />
                </div>  
            </DialogContent>

        </Dialog>

    );
}