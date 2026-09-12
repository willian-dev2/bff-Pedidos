package dev.java.bffpedidos.business.Service.gestaoService;

import dev.java.bffpedidos.business.DTOS.gestaoDTO.CompraDTO;
import dev.java.bffpedidos.infrastructure.Client.GestaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final GestaoClient client;


    public CompraDTO comprarProduto(Long id, String token) {

        return client.comprarProduto(id, token);

    }

    //

    public CompraDTO buscarPorId(Long id, String token) {

        return client.comprarProduto(id, token);
    }



}
