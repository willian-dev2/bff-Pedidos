package dev.java.bffpedidos.business.Service;

import dev.java.bffpedidos.business.DTOS.UsuarioDTO;
import dev.java.bffpedidos.infrastructure.Client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        return client.salvarUsuario(usuarioDTO);
    }


    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return client.login(usuarioDTO);
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return client.buscarUsuarioPorEmail(email, token);
    }


    public void deletarUsuarioPorEmail(String email, String token) {
        client.deletarUsuarioPorEmail(email, token);
    }


    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO usuarioDTO, String email) {
        return client.atualizarDadosUsuario(token, usuarioDTO, email);
    }

}
