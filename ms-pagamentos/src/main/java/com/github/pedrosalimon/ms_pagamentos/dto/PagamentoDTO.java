package com.github.pedrosalimon.ms_pagamentos.dto;

import com.github.pedrosalimon.ms_pagamentos.enteties.Pagamento;
import com.github.pedrosalimon.ms_pagamentos.enteties.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {

    private Long id;
    @NotNull(message = "O campo de valor é obrigatório")
    @Positive(message = "O valor do pagamento deve ser um número positivo")
    private BigDecimal valor;
    @NotBlank(message = "O campo de nome do cartão é obrigatório")
    @Size(min = 3, max = 50, message = "O campo de nome do cartão deve ter entre 3 e " +
            "50 caracteres")
    private String nome;
    @NotBlank(message = "O campo de número do cartão é obrigatório")
    @Size(min = 16, max = 16, message = "O campo número do cartão deve ter 16 caracteres")
    private String numeroCartao;
    @NotBlank(message = "O campo de validade é obrigatório")
    @Size(min = 5, max = 5, message = "O campo validade deve ter 3 caracteres")
    private String validade;
    @NotBlank(message = "O campo de cvv é obrigatório")
    @Size(min = 3, max = 3, message = "O campo cvv deve ter 3 caracteres")
    private String cvv;
    private Status status;
    @NotNull(message = "O campo ID do pedido é obrigatório")
    private Long idPedido;

    public PagamentoDTO(Pagamento pagamento){
        id = pagamento.getId();
        valor = pagamento.getValor();
        nome = pagamento.getNome();
        numeroCartao = pagamento.getNumeroCartao();
        validade = pagamento.getValidade();
        cvv = pagamento.getCvv();
        status = pagamento.getStatus();
        idPedido = pagamento.getIdPedido();
    }
}
