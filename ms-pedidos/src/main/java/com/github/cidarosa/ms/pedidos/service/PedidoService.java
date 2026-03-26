package com.github.cidarosa.ms.pedidos.service;

import com.github.cidarosa.ms.pedidos.dto.PedidoDto;
import com.github.cidarosa.ms.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDto> findAllPedidos(){

        return pedidoRepository.findAll()
                .stream().map(PedidoDto::new).toList();
    }
}
