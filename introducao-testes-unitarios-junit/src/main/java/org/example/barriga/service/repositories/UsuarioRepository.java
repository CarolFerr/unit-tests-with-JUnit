package org.example.barriga.service.repositories;

import org.example.barriga.domain.Usuario;

import java.util.Optional;

// This is a contract that service layer firm with the external layer (database, API, etc)
public interface UsuarioRepository {

    // Add the user to the database and return the saved user
    Usuario salvar (Usuario usuario);

    // Because I don't know if the user exists with te email or not, I use Optional
    Optional<Usuario> getUserByEmail(String email);

}
