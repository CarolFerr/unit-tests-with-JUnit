package org.example.barriga.domain;
import org.example.barriga.domain.exceptions.ValidationException;

import java.util.Objects;

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
        if (senha == null) {
            throw new ValidationException("Senha é obrigatória");
        } else if (senha.length() < 6) {
            throw new ValidationException("Senha deve ter no mínimo 6 caracteres");
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    // These methods are overridden to ensure proper comparison and hashing of Usuario objects
    // This is important for collections and when checking for equality between two Usuario instances

    // This method checks if two Usuario objects are equal based on their attributes
    // Objects is a utility class that provides static methods for operating on objects
    // Create an analogy, we can say that Objects are like the DNA of the object, defining its identity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha);
    }

    // This method generates a hash code for the Usuario object based on its attributes
    // The purpose of hashCode is to provide a unique identifier for the object in hash-based collections
    // Creating an analogy, we can say that hashCode is like a fingerprint for the object
    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}




