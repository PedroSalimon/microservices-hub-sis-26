package com.github.pedrosalimon.ms_pagamentos.service;

import com.github.pedrosalimon.ms_pagamentos.dto.PagamentoDTO;
import com.github.pedrosalimon.ms_pagamentos.enteties.Pagamento;
import com.github.pedrosalimon.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.pedrosalimon.ms_pagamentos.repository.PagamentoRepository;
import com.github.pedrosalimon.ms_pagamentos.tests.Factory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PagamentoTestService {
    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Long existingId;
    private Long nonExistingId;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        existingId = 1l;
        nonExistingId = Long.MAX_VALUE;

        pagamento = Factory.createPagamento();
    }

    @Test
    void deletePagamentoByIdShouldDeleteWhenIdExists() {
        //Arrange - prepara o comportamento do mock (stubbing)
        Mockito.when(pagamentoRepository.existsById(existingId)).thenReturn(true);

        pagamentoService.deletePagamento(existingId);

        //verify() = verifica e o mock foi chamado
        //Verifica que o mock pagamentoRepository receneu uma chamada ao metodo existsByID
        Mockito.verify(pagamentoRepository).existsById(existingId);
        //Verifica
        Mockito.verify(pagamentoRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    @DisplayName("deletePagamento deveria lançar ResourceNotFoundException quando o Id não existir")
    void deletePagementoByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(pagamentoRepository.existsById(nonExistingId)).thenReturn(false);
        //Act+Assert
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    pagamentoService.deletePagamento(nonExistingId);
                });

        //Verificações (behavior)
        Mockito.verify(pagamentoRepository).existsById(nonExistingId);
        //never() = equivalente a times(0) -> esse metodo não pode ter sido cahamdo nenhuma vez
        //anylong() é um matcher (coringa): aceita qualquer valor long/long
        Mockito.verify(pagamentoRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }

    @Test
    void findPagamentoByIdShouldReturnPagamentoDTOWhenIdExists() {
        //Arrange
        Mockito.when(pagamentoRepository.findById(existingId))
                .thenReturn(Optional.of(pagamento));

        //Act
        PagamentoDTO result = pagamentoService.findPagamentoById(existingId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(pagamento.getId(), result.getId());
        Assertions.assertEquals(pagamento.getValor(), result.getValor());

        Mockito.verify(pagamentoRepository).findById(existingId);
        Mockito.verifyNoMoreInteractions(pagamentoRepository);

    }


}
