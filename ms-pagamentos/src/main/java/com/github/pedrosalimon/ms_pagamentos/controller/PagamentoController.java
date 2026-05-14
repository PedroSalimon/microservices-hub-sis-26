package com.github.pedrosalimon.ms_pagamentos.controller;

import com.github.pedrosalimon.ms_pagamentos.dto.PagamentoDTO;
import com.github.pedrosalimon.ms_pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAll(){
        List<PagamentoDTO> pagamentoDTOS = pagamentoService.findAllPagamento();
        return ResponseEntity.ok(pagamentoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        PagamentoDTO pagamentoDTO= pagamentoService.findPagamentoById(id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PostMapping
    private ResponseEntity<PagamentoDTO> createPagamento(@RequestBody
                                                         @Valid PagamentoDTO pagamentoDTO) {
        pagamentoDTO = pagamentoService.savePagamento(pagamentoDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pagamentoDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pagamentoDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PagamentoDTO> updatePagamento(@PathVariable Long id,
                                                         @RequestBody
                                                         @Valid PagamentoDTO pagamentoDTO){
        pagamentoDTO = pagamentoService.updatePagamento(id, pagamentoDTO);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePagamento (@PathVariable Long id) {
        pagamentoService.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<PagamentoDTO> confirmarPagamentoDoPedido(@PathVariable
                                                                   @NotNull Long id) {
        PagamentoDTO dto = pagamentoService.confirmarPagamentoDoPedido(id);
        return ResponseEntity.ok(dto);
    }
}