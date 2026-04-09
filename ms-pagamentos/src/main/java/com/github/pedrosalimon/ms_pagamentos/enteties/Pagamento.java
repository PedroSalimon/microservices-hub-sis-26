package com.github.pedrosalimon.ms_pagamentos.enteties;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private String nome; //nome do cartão
    @Column(nullable = false, length = 16)
    private String numeroCartao;
    @Column(nullable = false, length = 5)
    private String validade;
    @Column(nullable = false, length = 3)
    private String cvv;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private Long idPedido;

}
