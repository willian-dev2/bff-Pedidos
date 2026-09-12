package dev.java.bffpedidos.business.DTOS.gestaoDTO;


import dev.java.bffpedidos.infrastructure.enums.Categoria;
import dev.java.bffpedidos.infrastructure.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private String nomeProduto;
    private String descricao;
    private Double preco;
    private Status status;
    private Categoria categoria;

}
