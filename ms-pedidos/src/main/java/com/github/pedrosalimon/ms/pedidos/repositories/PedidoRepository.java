package com.github.pedrosalimon.ms.pedidos.repositories;

import com.github.pedrosalimon.ms.pedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
