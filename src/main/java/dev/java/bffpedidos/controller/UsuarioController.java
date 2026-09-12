package dev.java.bffpedidos.controller;


import dev.java.bffpedidos.business.Service.UsuarioService;
import dev.java.bffpedidos.business.DTOS.UsuarioDTO;
import dev.java.bffpedidos.infrastructure.Client.UsuarioClient;
import dev.java.bffpedidos.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioClient usuarioClient;


    @PostMapping("/registro")
    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioClient.login(usuarioDTO));

    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar dados de usuários por email",
            description = "busca dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encotrado")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }


    @DeleteMapping("/delete/{email}")
    @Operation(summary = "Deletar usuário por email", description = "Deleta usuário se tiver autorização")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encotrado")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Dados de Usuários", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encotrado")
    @ApiResponse(responseCode = "409", description = "Usuário não tem autorização admin para acessar essa função")
    @ApiResponse(responseCode = "500", description = "Erro de servido")
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario(@RequestHeader(value = "Authorization", required = false) String token,
                                                            @RequestBody UsuarioDTO dto,
                                                            @RequestParam("email") String email) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto, email));
    }

}
