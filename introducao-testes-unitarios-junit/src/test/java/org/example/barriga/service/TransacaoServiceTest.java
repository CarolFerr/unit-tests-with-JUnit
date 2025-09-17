package org.example.barriga.service;

import org.example.barriga.domain.Conta;
import org.example.barriga.domain.Transacao;
import org.example.barriga.domain.builders.TransacaoBuilder;
import org.example.barriga.domain.exceptions.ValidationException;
import org.example.barriga.service.external.ClockService;
import org.example.barriga.service.repositories.TransacaoDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.example.barriga.domain.builders.ContaBuilder.umaConta;
import static org.example.barriga.domain.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    //@Spy
    @InjectMocks
    private TransacaoService service;
    // Had not a constructor even DAO
    @Mock
    private TransacaoDAO dao;

    @Mock
    private ClockService clockService;

    @BeforeEach
    void setup() {
        when(clockService.getCurrentDateTime()).thenReturn(LocalDateTime.of(2025, 9, 15, 9, 0, 20));
        // Use when for spy
        //when(service.getTime()).thenReturn(LocalDateTime.of(2025, 9, 15, 9, 0, 20));
    }

//    void checkTime() {
//        // Avoid run test that will fail, so is like said to de code "don't run this test"
//        Assumptions.assumeTrue(LocalDateTime.now().getHour() >= 10 && LocalDateTime.now().getHour() < 22);
//    }

    @Test
    void shouldSaveTranctionValid() {

        Transacao transacaoParaSalvar = umTransacao().comId(null).agora();

        when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());


//        LocalDateTime dataDesejada = LocalDateTime.of(2025,9,15,9,0, 20);
//        System.out.println(dataDesejada);
//
//        try(MockedStatic<LocalDateTime> ldt = Mockito.mockStatic(LocalDateTime.class)) {
//            ldt.when(() -> LocalDateTime.now()).thenReturn(dataDesejada);
//            System.out.println(LocalDateTime.now());

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        assertEquals(umTransacao().agora(), transacaoSalva);
        assertAll("Transação",
                () -> assertEquals(1L, transacaoSalva.getId()),
                () -> assertEquals("Transação Válida", transacaoSalva.getDescricao()),
                () -> {
                    assertAll("Conta",
                            () -> assertEquals("Conta Válida", transacaoSalva.getConta().nome()),
                            () -> {
                                assertAll("Usuário",
                                        () -> assertEquals("Usuário Válido", transacaoSalva.getConta().usuario().getNome()),
                                        () -> assertEquals("123456", transacaoSalva.getConta().usuario().getSenha())
                                );
                            }
                    );
                }
        );
    }

    @ParameterizedTest(name = "{6}") // Get the index of the parameter to show in the test name
    @MethodSource(value = "cenariosObrigatorios")
    void shouldValidateRequiredFieldsWhenSavingTransaction(Long id, String descricao, Double valor, LocalDate data, Conta conta,
                                                           Boolean status, String mensagem) {

        String exMensagem = Assertions.assertThrows(ValidationException.class, () -> {
            Transacao transacao = umTransacao().comId(id).comDescricao(descricao).comValor(valor).comData(data).comConta(conta)
                    .comStatus(status).agora();
            service.salvar(transacao);
        }).getMessage();

        Assertions.assertEquals(mensagem, exMensagem);
    }

    static Stream<Arguments> cenariosObrigatorios() {
        return Stream.of(
                Arguments.of(1L, null, 10D, LocalDate.now(), umaConta().agora(), true, "Descrição é obrigatório"),
                Arguments.of(1L, "Descricao", null, LocalDate.now(), umaConta().agora(), true, "Valor é obrigatório"),
                Arguments.of(1L, "Descricao", 10D, null, umaConta().agora(), true, "Data é obrigatório"),
                Arguments.of(1L, "Descricao", 10D, LocalDate.now(), null, true, "Conta é obrigatório")

        );
    }

    // Similar of AssumeTrue
//    public static boolean isHoraValida() {
//        return LocalDateTime.now().getHour() >= 10 && LocalDateTime.now().getHour() < 22;
//    }

}

