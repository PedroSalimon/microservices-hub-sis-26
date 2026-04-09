package com.github.pedrosalimon.ms_pagamentos.service;

import com.github.pedrosalimon.ms_pagamentos.dto.PagamentoDTO;
import com.github.pedrosalimon.ms_pagamentos.enteties.Pagamento;
import com.github.pedrosalimon.ms_pagamentos.enteties.Status;
import com.github.pedrosalimon.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.pedrosalimon.ms_pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAllPagamento() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream().map(PagamentoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new PagamentoDTO(pagamento);
    }

    @Transactional(readOnly = true)
    public PagamentoDTO savePagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        mapperDtoToPagamento(pagamentoDTO, pagamento);
        pagamento.setStatus(Status.CRIADO);
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamento);
    }

    @Transactional(readOnly = true)
    public PagamentoDTO updatePagamento(Long id, PagamentoDTO pagamentoDTO){
        try {
            Pagamento pagamento = pagamentoRepository.getReferenceById(id);
            mapperDtoToPagamento(pagamentoDTO, pagamento);
            pagamento.setStatus(pagamentoDTO.getStatus());
            pagamento = pagamentoRepository.save(pagamento);
            return new PagamentoDTO(pagamento);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(readOnly = true)
    public void deletePagamento(Long id) {
        if (!pagamentoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: "+ id);
        }
        pagamentoRepository.deleteById(id);
    }

    private void mapperDtoToPagamento(PagamentoDTO pagamentoDTO, Pagamento pagamento) {
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setNome(pagamentoDTO.getNome());
        pagamento.setNumeroCartao(pagamento.getNumeroCartao());
        pagamento.setValidade(pagamento.getValidade());
        pagamento.setCvv(pagamento.getCvv());
        pagamento.setIdPedido(pagamento.getIdPedido());
    }

}
