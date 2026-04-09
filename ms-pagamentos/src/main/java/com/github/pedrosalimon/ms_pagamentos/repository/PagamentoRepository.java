package com.github.pedrosalimon.ms_pagamentos.repository;

import com.github.pedrosalimon.ms_pagamentos.enteties.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
