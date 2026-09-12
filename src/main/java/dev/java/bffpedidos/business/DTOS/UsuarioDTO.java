package dev.java.bffpedidos.business.DTOS;

import dev.java.bffpedidos.infrastructure.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private Role role;

}
