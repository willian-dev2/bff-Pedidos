package dev.java.bffpedidos.infrastructure.Client;

import dev.java.bffpedidos.business.DTOS.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/buscar")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);

    @PostMapping("/registro")
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("/delete/{email}")
    Void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);


    @PutMapping("/atualizar")
    UsuarioDTO atualizarDadosUsuario(@RequestHeader("Authorization") String token,
                                     @RequestBody UsuarioDTO dto,
                                     @RequestParam("email") String email);

}
