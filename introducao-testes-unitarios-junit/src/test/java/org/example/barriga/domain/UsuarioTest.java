package org.example.barriga.domain;

import org.example.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domain: Usuario")
public class UsuarioTest {

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

//    @Test
//    @DisplayName("Deve lançar ValidationException quando nome for nulo")
//    void shouldThrowValidationExceptionWhenNameIsNull() {
//        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
//                // without criadoAgora method, only builder method will be called and never will create the instance of Usuario
//                umUsuario().comNome(null).agora()); // give me the instance of Usuario
//                // new Usuario(1L, null, "usuario@email.com", "123456"));
//        assertEquals("Nome é obrigatório", exception.getMessage());
//
//    }
//
//    @Test
//    @DisplayName("Deve lançar ValidationException quando email for nulo")
//    void shouldThrowValidationExceptionWhenEmailIsNull() {
//        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
//                umUsuario().comEmail(null).agora()); // new Usuario(1L, "Usuário Válido", null, "123456"));
//        assertEquals("Email é obrigatório", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve lançar ValidationException quando senha for nulo")
//    void shouldThrowValidationExceptionWhenPasswordIsNull() {
//        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
//                umUsuario().comSenha(null).agora()); // new Usuario(1L, "Usuário Válido", "null"
//        assertEquals("Senha é obrigatória", exception.getMessage());
//    }

    @Test
    @DisplayName("Deve lançar ValidationException quando senha tiver menos de 6 caracteres")
    void shouldThrowValidationExceptionWhenPasswordHasLessThan6Characters() {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                umUsuario().comSenha("12345").agora());
        assertEquals("Senha deve ter no mínimo 6 caracteres", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar sucesso quando senha tiver exatamente 6 caracteres")
    void shouldReturnSuccessWhenPasswordHasExactly6Characters() {
        Assertions.assertDoesNotThrow(() ->
                umUsuario().comSenha("123456").agora());
    }

    @ParameterizedTest
    @DisplayName("Deve lançar ValidationException quando algum dado for nulo")
    @CsvSource(value = {
            "null, usuario@email.com, 123456", // nome null
            "Usuário Válido, null, 123456", // email null
            "Usuário Válido, usuario@email.com, null", // senha null
            "null, null, 123456" // nome e email null
    })
    void shouldThrowValidationExceptionWhenDataIsNull(String nome, String email, String senha) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                // ? ternary operator -> condição ? valorSeVerdadeiro : valorSeFalso
                // if the string is "null", pass null to the builder, else pass the value
                // For example: "null".equals(nome) is the condition
                // if true, return null, else return nome
                umUsuario().comNome("null".equals(nome) ? null : nome).
                        comEmail("null".equals(email) ? null : email)
                        .comSenha("null".equals(senha) ? null : senha)
                        .agora());
        // This assertion uses a nested ternary operator to determine the expected message
        // based on which parameter is null. It checks nome first, then email, and finally senha.
        // For example, if nome is null, the message will be "Nome é obrigatório".
        // If email is null, the message will be "Email é obrigatório", and so on.
        assertEquals("null".equals(nome) ? "Nome é obrigatório" :
                "null".equals(email) ? "Email é obrigatório" :
                        "Senha é obrigatória", exception.getMessage());

    }

    // Another way to do the same test above
    // That cenario is better than the previous one, because it is more readable
    // And it is easier to maintain, because if we need to change the message, we
    // can change it in one place only
    // And it is easier to add more test cases, because we can add more lines
    // in the CsvSource
    // And it is easier to understand, because we can see the expected message
    // for each test case
    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvSource(value = {
            "1, NULL, usuario@email.com, 123456, Nome é obrigatório", // nome null
            "1, Usuário Válido, NULL, 123456, Email é obrigatório", // email null
            "1, Usuário Válido, usuario@email.com, NULL, Senha é obrigatória", // senha null
    }, nullValues = "NULL" // Define "NULL" como valor nulo
    )
    @DisplayName("Deve lançar ValidationException quando algum dado for nulo - 2ª forma")
    void shouldThrowValidationExceptionWhenDataIsNull_SecondWay(Long id, String nome, String email, String senha, String mensagemEsperada) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                umUsuario().comId(id)
                        .comNome(nome)
                        .comEmail(email)
                        .comSenha(senha)
                        .agora());
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    // Externalisation Data
    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvFileSource(files = "src//test//resources//camposObrigatoriosUsuario.csv", nullValues = "NULL", numLinesToSkip = 1)
    @DisplayName("Deve lançar ValidationException quando algum dado for nulo - com dados externalizados")
    void shouldThrowValidationExceptionWhenDataIsNull_ExternalData(Long id, String nome, String email, String senha, String mensagemEsperada) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                umUsuario().comId(id)
                        .comNome(nome)
                        .comEmail(email)
                        .comSenha(senha)
                        .agora());
        assertEquals(mensagemEsperada, exception.getMessage());
    }

}
