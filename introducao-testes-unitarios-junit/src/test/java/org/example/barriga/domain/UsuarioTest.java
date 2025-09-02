package org.example.barriga.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domain: Usuario")
class UsuarioTest {

    @Test
    @DisplayName("Deve criar usuário com dados válidos")
    void shouldCreateUserWithValidData() {
        Usuario usuario = new Usuario(1L, "Usuário Válido", "usuario@email.com", "123456");
        Assertions.assertAll("Usuário",
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("Usuário Válido", usuario.getNome()),
                () -> assertEquals("usuario@email.com", usuario.getEmail()),
                () -> assertEquals("123456", usuario.getSenha())
        );
    }
}
