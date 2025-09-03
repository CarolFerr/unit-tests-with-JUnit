package org.example.barriga.domain;
import org.example.barriga.domain.exceptions.ValidationException;

// Simple POJO (Plain Old Java Object) class representing a User entity
// It encapsulates user attributes and provides a constructor and getter methods
// This object is immutable after creation, as there are no setter methods
// This design is useful for ensuring that user data remains consistent and unchanged after instantiation

public class Usuario {
    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        validar(nome, email, senha);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    private void validar(String nome, String email, String senha) {
        if (nome == null) throw new ValidationException("Nome é obrigatório");
        if (email == null) throw new ValidationException("Email é obrigatório");
        if (senha == null) throw new ValidationException("Senha é obrigatória");
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}




