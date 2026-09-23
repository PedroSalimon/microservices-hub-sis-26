package com.github.pedrosalimon.ms.pedidos.entities;

public enum Status {

    CRIADO,
    PAGAMENTO_PENDENTE,
    PAGO,
    CONFIRMADO,
    FALHA_PAGAMENTO,
    SAIU_PARA_ENTREGA,
    ENTREGUE,
    CANCELADO
}
