package com.github.cidarosa.ms.pedidos.repositories;

import com.github.cidarosa.ms.pedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
