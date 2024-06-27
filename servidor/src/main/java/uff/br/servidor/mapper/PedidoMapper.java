package uff.br.servidor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.request.PedidoPostRequestBody;

@Component
@RequiredArgsConstructor
public class PedidoMapper {
    private final UsuarioRepository usuarioRepository;

    public Pedido toPedido(PedidoPostRequestBody pedidoPostRequestBody){
        System.out.println(pedidoPostRequestBody.getUsuario());
        return Pedido.builder()
                .usuario(usuarioRepository.findById(pedidoPostRequestBody.getUsuario())
                        .orElseThrow(()-> new BadRequestException("Nome do usuario nao encontrado")))
                .status(Status.valueOf(pedidoPostRequestBody.getStatus()))
                .build();
    }
}
