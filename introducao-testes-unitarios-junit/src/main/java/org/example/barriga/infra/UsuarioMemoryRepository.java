package org.example.barriga.infra;

import org.example.barriga.domain.Usuario;
import org.example.barriga.service.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> users;
    private Long currentId; // simulate auto increment in database

    public UsuarioMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        // Every time I create a new instance of this repository, it will have one user
        // How is in memory every time this instance it will be destroyed we lost all data
        salvar(new Usuario(null, "User # 1", "user1@email.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Increment the id and return the new value
    private Long nextId() { return  ++currentId;}

    public void printUsers() {
        System.out.println(users);
    }

    // Simple main method to test the repository
    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.salvar(new Usuario(null, "User # 2", "user2@email.com", "654321"));
        repo.printUsers();
    }
}
