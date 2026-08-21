package br.com.bradescoSantanderDio.DesignPattern.repository;


import br.com.bradescoSantanderDio.DesignPattern.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;


// O path = "pedidos" define que a URL será /pedidos
@RepositoryRestResource(collectionResourceRel = "pedidos", path = "pedidos")
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}