CREATE TABLE IF NOT EXISTS tb_pagamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    valor DECIMAL(10,2) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    numero_cartao VARCHAR(16) NOT NULL,
    validade VARCHAR(5) NOT NULL,
    codigo_seguranca VARCHAR(3) NOT NULL,
    status VARCHAR(35) NOT NULL,
    id_pedido BIGINT NOT NULL.
    CONSTRAINT pk_tb_pagamentos PRIMARY KEY (id)
    );