package org.example.barriga.domain;

import org.example.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.example.barriga.domain.builders.ContaBuilder.umaConta;
import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContaTest {

    @Test
    void shouldCreateAccountSuccessfully() {
        Conta conta = umaConta().agora();
        Assertions.assertAll("Conta",
                () -> assertEquals(1L, conta.id()),
                () -> assertEquals("Conta Válida", conta.nome()),
                () -> assertEquals(umUsuario().agora(), conta.usuario())
        );
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    void shouldRejectInvalidAccount(Long id, String nome, Usuario usuario, String mensagem) {
        String errorMessage =Assertions.assertThrows(ValidationException.class, () ->
                umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
        Assertions.assertEquals(mensagem, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().agora(), "Nome é obrigatório"),
                Arguments.of(1L, "Conta Válida", null, "Usuário é obrigatório")
        );
    }

}
