package dev.java.bffpedidos.infrastructure.Client;

import dev.java.bffpedidos.business.DTOS.gestaoDTO.CompraDTO;
import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoDTO;
import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoResumoDTO;
import dev.java.bffpedidos.infrastructure.enums.Categoria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "gestao-pedidos", url = "${gestao-pedidos.url}")
public interface GestaoClient {


    @PostMapping("/produtos/registro")
    ProdutoDTO gravarProduto(@RequestBody ProdutoDTO dto,
                             @RequestHeader("Authorization") String token);


    @GetMapping("/produtos/categoria/{categoria}")
    List<ProdutoResumoDTO> buscarProduto(@PathVariable Categoria categoria);


    @GetMapping("/produtos/{id}")
    ProdutoDTO buscarPorId(@PathVariable Long id);

    @DeleteMapping("/produtos/delete/{id}")
    Void deletarProdutoPorId(@PathVariable Long id,
                             @RequestHeader("Authorization") String token);

    @PatchMapping("/produtos/atualizar")
    ProdutoDTO atualizarDadosProduto(@RequestHeader("Authorization") String token,
                                     @RequestBody ProdutoDTO produtoDTO,
                                     @RequestParam("id") Long id);

    @GetMapping("/produtos")
    List<ProdutoDTO> listarTodos(@RequestHeader("Authorization") String token);



    // Controller Compras

    @PostMapping("/compras/{produtoId}")
    CompraDTO comprarProduto(@PathVariable Long produtoId,
                             @RequestHeader("Authorization") String token);


    @GetMapping("/compras/{id}")
    CompraDTO buscarPorId(@PathVariable Long id,
                          @RequestHeader("Authorization") String token);

}
