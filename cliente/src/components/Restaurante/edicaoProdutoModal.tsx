import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
    DialogFooter
  } from "@/components/ui/dialog"
  import { Input } from "@/components/ui/input"
  import {Button} from "@/components/ui/button"
  import { Textarea } from "@/components/ui/textarea"
  export function EdicaoProdutoModal(){

      const handleSave = () => {
        console.log("sucesso");
      }

      return(
         <Dialog>
            <DialogTrigger>
                    <Button
                                
                    className="text-sky-700 border-2 bg-blue-100  border-sky-700 hover:bg-sky-700 hover:text-white"
                    >
                                Editar
                            </Button>
                       
            </DialogTrigger>
    <DialogContent>
        <DialogHeader>
            <DialogTitle>Edição do Produto</DialogTitle>
        </DialogHeader>
        <div className="grid gap-4 py-4">
    <div className="grid grid-cols-4 items-center gap-4">
        <label htmlFor="name" className="text-right">
            Nome
        </label>
        <Input
            id="name"
            defaultValue="Hamburger"
            className="col-span-3"
        />
    </div>
    <div className="grid grid-cols-4 items-center gap-4">
        <label htmlFor="price" className="text-right">
            Preço
        </label>
        <Input
        id="price"
        onKeyDown={(e) => {
            if (!/[0-9,]/.test(e.key) && e.key !== 'Backspace' && e.key !== 'Delete') {
                e.preventDefault();
            }
        }}
        defaultValue="10"
    />
    </div>
    <div className="grid grid-cols-4 items-center gap-4">
        <label htmlFor="description" className="text-right">
            Descrição
        </label>
        <div className="col-span-3">
            <Textarea id="description" />
        </div>
    </div>
</div>
<DialogFooter >
        <Button className="text-green-600 border-2 border-green-600 hover:bg-green-600 hover:text-white"  onClick={handleSave}>Salvar mudanças</Button>
    </DialogFooter>
    </DialogContent>
   
</Dialog>
      
    );
}