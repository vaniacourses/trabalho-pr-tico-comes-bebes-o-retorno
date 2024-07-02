package uff.br.servidor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.ProdutoPedido;
import uff.br.servidor.model.Status;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findPedidosByUsuarioCpf(String cpf);
    Pedido findByUsuario_IdAndStatus(UUID id,Status status);
    Page<Pedido> findById(Pageable pageable, UUID id);
}
