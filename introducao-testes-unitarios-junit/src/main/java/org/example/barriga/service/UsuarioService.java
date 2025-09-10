package org.example.barriga.service;

import org.example.barriga.domain.Usuario;
import org.example.barriga.domain.exceptions.ValidationException;
import org.example.barriga.service.repositories.UsuarioRepository;

import java.util.Optional;

// This class has no idea about how the user is being saved
// This class has no idea about the database, SQL, etc.
// Who going to save need find a way to save and send for this class by constructor
public class UsuarioService {
    // Someone need to implement this interface, but this class doesn't know who
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        // If change de way of saving the user, it will be necessary change de logic here
        // Connect to database and save the user
        // Prepare and execute the SQL command
        // Execute the consult
        // Get the persistent user
        repository.getUserByEmail(usuario.getEmail())
                .ifPresent(user -> {
                    throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.getEmail()));});
        return repository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }
}
