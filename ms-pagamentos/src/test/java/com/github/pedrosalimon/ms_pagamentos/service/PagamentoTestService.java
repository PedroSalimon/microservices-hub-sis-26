package com.github.pedrosalimon.ms_pagamentos.service;

import com.github.pedrosalimon.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.pedrosalimon.ms_pagamentos.repository.PagamentoRepository;
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

@ExtendWith(MockitoExtension.class)
public class PagamentoTestService {
    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1l;
        nonExistingId = Long.MAX_VALUE;
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
}
