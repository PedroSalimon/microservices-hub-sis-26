package com.github.pedrosalimon.ms.pagamentos.repository;

import com.github.pedrosalimon.ms.pagamentos.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
