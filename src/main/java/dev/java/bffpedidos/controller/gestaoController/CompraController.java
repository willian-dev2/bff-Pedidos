package dev.java.bffpedidos.controller.gestaoController;

import dev.java.bffpedidos.business.DTOS.gestaoDTO.CompraDTO;
import dev.java.bffpedidos.business.Service.gestaoService.CompraService;
import dev.java.bffpedidos.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compras")
@Tag(name = "Compras", description = "Realiza compra no sistema")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class CompraController {

    private final CompraService compraService;

    @PostMapping("/{produtoId}")
    @Operation(summary = "Comprar produto do sistema", description = "Compra um produto escolhido no sistema")
    @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "409", description = "É necessário login para efetuar uma compra no sistema")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<CompraDTO> comprarProduto(@PathVariable Long produtoId,
                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(compraService.comprarProduto(produtoId, token));

    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca compra de usuário",
            description = "Usuário pode buscar suas próprias compras no sistema")
    @ApiResponse(responseCode = "200", description = "busca realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Compra não encontrada")
    @ApiResponse(responseCode = "409",
            description = "Usuário não tem autorização admin para acessar essa compras de outro email")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Long id,
                                                 @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(compraService.buscarPorId(id, token));
    }


}
