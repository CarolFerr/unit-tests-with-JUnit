package org.example.barriga.service;

import org.example.barriga.domain.Usuario;
import org.example.barriga.domain.builders.UsuarioBuilder;
import org.example.barriga.infra.UsuarioDummyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsuarioServiceTest {

    private UsuarioService service;

    @Test
    void shouldSaveUserSuccessfully() {
        service = new UsuarioService(new UsuarioDummyRepository());
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("novoUsuario@email.com").agora();
        Usuario savedUser = service.salvar(user);
        Assertions.assertNotNull(savedUser.getId());
    }
}
