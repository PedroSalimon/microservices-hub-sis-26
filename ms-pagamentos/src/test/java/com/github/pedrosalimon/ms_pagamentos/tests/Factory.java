package com.github.pedrosalimon.ms_pagamentos.tests;

import com.github.pedrosalimon.ms_pagamentos.enteties.Pagamento;
import com.github.pedrosalimon.ms_pagamentos.enteties.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento createPagamento() {
        Pagamento pagamento = new Pagamento(1L, BigDecimal.valueOf(32.25),
                "Briannede Tarth", "7418529637423612",
                "07/15", "345", Status.CRIADO, 1L);
        return pagamento;
    }
    public static Pagamento createPagamentoSemId() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);
        return pagamento;
    }

}
