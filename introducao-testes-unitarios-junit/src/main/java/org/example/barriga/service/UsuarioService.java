package org.example.barriga.service;

import org.example.barriga.domain.Usuario;
import org.example.barriga.service.repositories.UsuarioRepository;

// This class has no idea about how the user is being saved
// This class has no idea about the database, SQL, etc
// Who gonna save need find a way to save and send for this class by constructor
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        // If change de way of saving the user, it will be necessart change de logic here
        // Conect to database and save the user
        // Prepare and execute the SQL command
        // Execute the consult
        // Get the persistent user
        return repository.salvar(usuario);}
}
