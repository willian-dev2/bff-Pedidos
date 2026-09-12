package dev.java.bffpedidos.controller.gestaoController;

import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoDTO;
import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoResumoDTO;
import dev.java.bffpedidos.business.Service.gestaoService.ProdutoService;
import dev.java.bffpedidos.infrastructure.enums.Categoria;
import dev.java.bffpedidos.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "cadastra produtos no sistema")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/registro")
    @Operation(summary = "Salvar produtos no sistema", description = "Cria um novo produto disponível para compra")
    @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<ProdutoDTO> gravarProduto(@RequestBody ProdutoDTO dto,
                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(produtoService.salvarProduto(token ,dto));
    }

    //

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "busca produto pela categoria do item", description = "buscar todos os produtos da mesma categoria")
    @ApiResponse(responseCode = "200", description = "Produtos da categoria encontrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<List<ProdutoResumoDTO>> buscarProduto(@PathVariable Categoria categoria) {

        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoria));
    }

    //

    @GetMapping("/{id}")
    @Operation(summary = "busca produto por Id", description = "buscar produto específico por Id")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(produtoService.buscarPorID(id));
    }

    //

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Deleta produto por Id", description = "Deleta produto cadastrado por Id")
    @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id,
                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        produtoService.deletarProdutoPorId(id, token);
        return ResponseEntity.ok().build();
    }

    //

    @PatchMapping("/atualizar")
    @Operation(summary = "Atualizar Dados do Produto", description = "Atualiza Dados Do Produto")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<ProdutoDTO> atualizarDadosProduto(@RequestHeader(value = "Authorization", required = false) String token,
                                                            @RequestBody ProdutoDTO produtoDTO,
                                                            @RequestParam("id") Long id) {
        return ResponseEntity.ok(produtoService.atualizarDadosProduto(token, produtoDTO, id));
    }

    //

    @GetMapping@Operation(summary = "Lista todos os produtos do banco de dados",
            description = "lista todos os produtos do banco de dados junto com suas informações ")
    @ApiResponse(responseCode = "200", description = "Produtos Listados com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<List<ProdutoDTO>> listarTodos(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(produtoService.listarTodos(token));
    }

}
