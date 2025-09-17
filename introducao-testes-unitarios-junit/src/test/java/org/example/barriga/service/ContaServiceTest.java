package org.example.barriga.service;

import org.example.barriga.domain.Conta;
import org.example.barriga.domain.builders.ContaBuilder;
import org.example.barriga.domain.exceptions.ValidationException;
import org.example.barriga.service.external.ContaEvent;
import org.example.barriga.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;

import static org.example.barriga.domain.builders.ContaBuilder.umaConta;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// In general this is used to avoid unnecessary warnings about unused stubbings in tests where strict stubbing is not required.
//@MockitoSettings(strictness = Strictness.LENIENT)
class ContaServiceTest {

    @InjectMocks
    private ContaService service;
    @Mock
    private ContaRepository repository;
    @Mock
    private ContaEvent event;

    @Captor
    private ArgumentCaptor<Conta> contaCaptor;

    @Test
    void shouldSaveFirstAccountWithSuccess() throws Exception {
        Conta contaToSave = umaConta().comId(null).agora();
        // This Mockito.any is a generic matcher that matches any object of the specified type. But in the end is
        // necessary valid argument
        // In other words, when the repository's save method is called with contaToSave, it should return a Conta object with an ID.
        when(repository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
        // Useful to mock void methods
        Mockito.doNothing().when(event).dispatch(umaConta().agora(), ContaEvent.EventType.CREATED);

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());

        // What real object was passed to the repository's save method?
        Mockito.verify(repository).salvar(contaCaptor.capture()); // capture the object
        Assertions.assertNull(contaCaptor.getValue().id());
        Assertions.assertTrue(contaCaptor.getValue().nome().startsWith("Conta Válida"));
    }

    @Test
    void shouldSaveSecondAccountWithSuccess() {
        Conta contaToSave = umaConta().comId(null).agora();
        when(repository.obterContasPorUsuario(contaToSave.usuario().getId()))
                .thenReturn(Collections.singletonList(umaConta().comNome("Conta2").agora()));
        when(repository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());
    }

    @Test
    void shouldRejectRepeatedConta() {
        Conta contaToSave = umaConta().comId(null).agora();
        when(repository.obterContasPorUsuario(contaToSave.usuario().getId()))
                .thenReturn(Arrays.asList(umaConta().agora()));
        // When was necessary to make the test pass with the stub, use the annotation @MockitoSettings(strictness = Strictness.LENIENT)
//        when(repository.salvar(contaToSave)).thenReturn(umaConta().agora());

        String mensagem = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Usuário já possui uma conta com este nome", mensagem);
    }

    @Test
    void shouldNotKeepAccountWithoutEvent() throws Exception {
        Conta contaToSave = umaConta().comId(null).agora();
        Conta contaSalva = umaConta().agora();

        when(repository.salvar(Mockito.any(Conta.class))).thenReturn(contaSalva);

        // Useful to mock void methods
        Mockito.doThrow(new Exception("Falha na operação!"))
                .when(event).dispatch(contaSalva, ContaEvent.EventType.CREATED);

        String mensagem = Assertions.assertThrows(Exception.class, () ->
                service.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Falha na criação da conta, tente novamente!", mensagem);

        Mockito.verify(repository).delete(contaSalva);
    }
}