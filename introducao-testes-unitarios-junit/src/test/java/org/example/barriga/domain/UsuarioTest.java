package org.example.barriga.domain;

import org.example.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domain: Usuario")
class UsuarioTest {

    @Test
    @DisplayName("Deve criar usuário com dados válidos")
    void shouldCreateUserWithValidData() {
        // Usuario usuario = new Usuario(1L, "Usuário Válido", "usuario@email.com", "123456");
        // Now using the builder
        Usuario usuario = umUsuario().agora();
        Assertions.assertAll("Usuário",
                () -> assertEquals(1L, usuario.getId()),
                () -> assertEquals("Usuário Válido", usuario.getNome()),
                () -> assertEquals("usuario@email.com", usuario.getEmail()),
                () -> assertEquals("123456", usuario.getSenha())
        );
    }

    @Test
    @DisplayName("Deve lançar ValidationException quando nome for nulo")
    void shouldThrowValidationExceptionWhenNameIsNull() {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                // without criadoAgora method, only builder method will be called and never will create the instance of Usuario
                umUsuario().comNome(null).agora()); // give me the instance of Usuario
                // new Usuario(1L, null, "usuario@email.com", "123456"));
        assertEquals("Nome é obrigatório", exception.getMessage());

    }
}
