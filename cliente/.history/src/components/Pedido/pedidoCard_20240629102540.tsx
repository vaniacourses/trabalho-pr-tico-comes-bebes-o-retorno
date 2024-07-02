
import { Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,} from "@/components/ui/card"
import {Button} from "@/components/ui/button"



    return(

        <Card>  
            <CardHeader>
                <CardTitle className="text-2xl text-center">Hamburguer</CardTitle>
                <CardDescription className="text-base" >Carne e queijo</CardDescription>
            </CardHeader>
            <CardContent>
                <img src={Lanche} alt="Lanche" className="object-cover rounded-md"/>
            </CardContent>
            <CardFooter className="flex flex-row justify-between">
            
            </CardFooter>
        </Card>
    )
}