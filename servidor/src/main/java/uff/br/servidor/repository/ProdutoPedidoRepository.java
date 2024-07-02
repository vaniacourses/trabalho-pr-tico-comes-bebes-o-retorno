package uff.br.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.ProdutoPedido;

import java.util.List;
import java.util.UUID;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, UUID> {
    List<ProdutoPedido> findProdutoPedidoByPedido_Id(UUID id);
}
