package br.com.bradescoSantanderDio.DesignPattern.repository;


import br.com.bradescoSantanderDio.DesignPattern.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A anotação @Repository diz ao Spring que essa interface vai lidar com o banco de dados
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}