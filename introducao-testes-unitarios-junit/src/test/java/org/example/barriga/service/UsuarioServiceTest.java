package org.example.barriga.service;

import org.example.barriga.domain.Usuario;
import org.example.barriga.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.example.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    // Means I want the mockito create it a mock for this class
    @Mock
    private UsuarioRepository repository;
    // Means I want the mockito to inject the mocks created in this class
    @InjectMocks
    private UsuarioService service;

    // This can be replaced by the annotation @ExtendWith(MockitoExtension.class)
    // This will initialize the mocks and inject them
//    private AutoCloseable closeable;
//
//    @BeforeEach
//    void setup() {
////        repository = Mockito.mock(UsuarioRepository.class);
////        service = new UsuarioService(repository);
//    // The classes above can be replaced by the annotation
//        closeable = MockitoAnnotations.openMocks(this);
//    }
//
//    // This method will be called after each test
//    // Avoid memory escape
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close(); // Fecha recursos
//    }

//    @Test
//    void shouldSaveUserSuccessfully() {
//        service = new UsuarioService(new UsuarioDummyRepository());
//        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("novoUsuario@email.com").agora();
//        Usuario savedUser = service.salvar(user);
//        Assertions.assertNotNull(savedUser.getId());
//    }

    // Using mockito
    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {

        // Interface non-implemented, we can use mockito to create a mock
        //  is like a fake implementation of the interface
        // In an analogy, mock is like a dummy that simulates the behavior of a real object in controlled ways

        when(service.getUserByEmail("novoUser@email.com"))
                .thenReturn(Optional.empty());

        Optional<Usuario> user = service.getUserByEmail("novoUser@email.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    void shouldReturnUserByEmail() {

        when(service.getUserByEmail("novoUser@email.com"))
                .thenReturn(Optional.of(
                        umUsuario().agora()
                ));

        Optional<Usuario> user = service.getUserByEmail("novoUser@email.com");
        Assertions.assertTrue(user.isPresent());
        System.out.println(user);

        // Verify if the method was called once
        // times verify how many times the method was called
//        verify(repository, times(2)).getUserByEmail("novoUser@email.com");
        // When the method was called at least once
        verify(repository, Mockito.atLeastOnce()).getUserByEmail("novoUser@email.com");
        // When the method was never called
        verify(repository, Mockito.never()).getUserByEmail("outroUser@email.com");
        // Verify if there were no more interactions with the mock, but if at least one interaction was made
        // this verification will fail
        Mockito.verifyNoMoreInteractions(repository);

        // Observation: try to don't use a lot of verifications in the same test
        // but test the behavior of flow of the method
    }

    @Test
    void shouldSaveUserSucessfully() {
        // Create a user without id
        Usuario userToSave = umUsuario().comId(null).agora();
        // When getUserByEmail is called with the email of userToSave, return Optional.empty()
        // This simulates that the user does not exist in the repository
        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.empty());
        // When salvar is called with userToSave, return a user with id (simulating that the user was saved and received an id)
        when(repository.salvar(userToSave))
                .thenReturn(umUsuario().agora());
        // Call the method to be tested
        Usuario savedUser = service.salvar(userToSave);
        // Verify that the returned user is not null and has an id
        Assertions.assertNotNull(savedUser.getId());
        // Verify that the methods in the repository were called as expected
        verify(repository).getUserByEmail(userToSave.getEmail());
        // Guarantee that the salvar method was called with userToSave
        verify(repository).salvar(userToSave);
    }
}
