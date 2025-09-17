package org.example.barriga.domain.builders;

import org.example.barriga.domain.Usuario;

// Builder class for creating instances of Usuario with a fluent interface
// This class follows the Builder design pattern to facilitate the construction of Usuario objects
// What I want to achieve with this class is to simplify the creation of Usuario objects in tests
// This way, I can create Usuario objects with default values and override only the attributes I want
// This improves the readability and maintainability of the test code
// It also helps to avoid duplication of code in tests
// The builder provides a fluent interface, allowing method chaining for setting attributes
public class UsuarioBuilderOld {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    //I don't want to execute this constructor
    // So, I make it private
    // This way, I prevent the instantiation of the class from outside
    // the scope of this class anyone can execute a new in this class, in other words
    // Anyone can create an instance of this class
    private UsuarioBuilderOld() {}

    // Static method to create a new instance of the builder
    // This method is the entry point to use the builder
    // Initializes the builder
    public static UsuarioBuilderOld umUsuario() {
        UsuarioBuilderOld builder = new UsuarioBuilderOld();
        inicializarDadosPadroes(builder);
        return builder;
    }

    // scoped static method to initialize default data
    private static void inicializarDadosPadroes(UsuarioBuilderOld builder) {
        builder.id = 1L;
        builder.nome = "Usuário Válido";
        builder.email = "usuario@email.com";
        builder.senha = "123456";
    }

    // Composition methods for each attribute
    // Acessed by one builder instance
    // In the scope of builder instance
    public UsuarioBuilderOld comId(Long id) {
        this.id = id;
        return this; // returning the current instance of the builder -> itself
    }

    public UsuarioBuilderOld comNome(String nome) {
        this.nome = nome;
        return this; // returning the current instance of the builder -> itself
    }

    public UsuarioBuilderOld comEmail(String email) {
        this.email = email;
        return this; // returning the current instance of the builder -> itself
    }

    public UsuarioBuilderOld comSenha(String senha) {
        this.senha = senha;
        return this; // returning the current instance of the builder -> itself
    }

    // Method to build the Usuario object with the specified attributes
    // This method creates and returns a new instance of Usuario
    // using the attributes set in the builder
    // This is the final step in the builder pattern
    public Usuario criadoAgora() {
        return new Usuario(id, nome, email, senha);
    }
}
