package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.PedidoMapper;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Status;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.request.PedidoPostRequestBody;
import uff.br.servidor.request.PedidoPutRequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    private final UsuarioRepository usuarioRepository;

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    public Page<Pedido> findByCpfUsuario(Pageable pageable, String cpf){
        return pedidoRepository.findByUsuario_Cpf(pageable, cpf);
    }

    public Pedido salvar(PedidoPostRequestBody pedidoPostRequestBody){
        return pedidoRepository.save(pedidoMapper.toPedido(pedidoPostRequestBody));
    }

    public void atualizar(PedidoPutRequestBody pedidoPutRequestBody){
        Pedido pedidoSalvo = findByIdOrElse(pedidoPutRequestBody.getId());

        pedidoSalvo.setUsuario(usuarioRepository.findById(pedidoPutRequestBody.getUsuario())
                .orElseThrow(()-> new BadRequestException("Nome do usuario nao encontrado")));
        pedidoSalvo.setStatus(Status.valueOf(pedidoPutRequestBody.getStatus()));

        pedidoRepository.save(pedidoSalvo);
    }

    public Pedido findByIdOrElse(UUID id){
        return pedidoRepository.findById(id).orElseThrow(()-> new BadRequestException("Id do pedido nao encontrado"));
    }

    public void deletar(UUID id){
        pedidoRepository.deleteById(id);
    }

}
