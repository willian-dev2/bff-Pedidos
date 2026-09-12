package dev.java.bffpedidos.business.Service.gestaoService;

import dev.java.bffpedidos.business.DTOS.UsuarioDTO;
import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoDTO;
import dev.java.bffpedidos.business.DTOS.gestaoDTO.ProdutoResumoDTO;
import dev.java.bffpedidos.infrastructure.Client.GestaoClient;
import dev.java.bffpedidos.infrastructure.enums.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final GestaoClient client;



    public ProdutoDTO salvarProduto(String token, ProdutoDTO dto) {

        return client.gravarProduto(dto, token);
    }

    //

    public List<ProdutoResumoDTO> buscarPorCategoria(Categoria categoria) {

        return client.buscarProduto(categoria);
    }


    //

    public ProdutoDTO buscarPorID(Long id) {

        return client.buscarPorId(id);
    }


    //

    public void deletarProdutoPorId(Long id, String token) {

        client.deletarProdutoPorId(id, token);
    }

    //


    public ProdutoDTO atualizarDadosProduto(String token, ProdutoDTO produtoDTO, Long id) {

        return client.atualizarDadosProduto(token, produtoDTO, id);
    }


    //


    public List<ProdutoDTO> listarTodos(String token) {

        return client.listarTodos(token);
    }


}
