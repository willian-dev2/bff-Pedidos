package dev.java.bffpedidos.business.DTOS.gestaoDTO;

import java.time.LocalDateTime;

public record CompraDTO(

        Long produtoId,
        Double valor,
        String nomeProduto

) {
}
