package org.example.barriga.infra;

import org.example.barriga.domain.Usuario;
import org.example.barriga.domain.exceptions.ValidationException;
import org.example.barriga.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;

// The annotation to define the order of test execution
// In other words, this annotation define that the order of test execution will be by the @Order annotation
// the Order annotation means the order of execution of the test
// If you don't use this annotation, the order of test execution will be random
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceComUserMemoryRepositoryTest {

    private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    // This kind of test is not recommended because the test depend on the order of execution
    // and this violate the principle of test isolation
    // in other words violate the F.I.R.S.T principle

    @Test
    @Order(1)
    void shouldSaveUserWhenValid() {
        Usuario user = service.salvar(umUsuario().comId(null).agora());
        // In memory repository always set the id when save
        Assertions.assertNotNull(user.getId());
//        Assertions.assertEquals(2L, user.getId());
    }

    @Test
    @Order(2)
    void shouldRejectExistentUser() {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(umUsuario().comId(null).agora()));
        Assertions.assertEquals("Usuário usuario@email.com já cadastrado!", exception.getMessage());
    }

}
