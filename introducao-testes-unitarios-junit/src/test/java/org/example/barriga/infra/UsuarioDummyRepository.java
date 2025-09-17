package org.example.barriga.infra;

import org.example.barriga.domain.Usuario;
import org.example.barriga.service.repositories.UsuarioRepository;

import java.util.Optional;

import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;

// Dummy class is kind of a class "boba"
// Dont worry about do something , its worry is about dont give problems
public class UsuarioDummyRepository implements UsuarioRepository {

    @Override
    public Usuario salvar(Usuario usuario) {
        return umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        // This prevents a NullPointerException from being returned

        if("usuario@email.com".equals(email))
            return Optional.of(umUsuario().comEmail(email).agora());
        return Optional.empty();
    }
}
