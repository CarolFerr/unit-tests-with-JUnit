package org.example.barriga.service;

import org.example.barriga.domain.Transacao;
import org.example.barriga.domain.exceptions.ValidationException;
import org.example.barriga.service.external.ClockService;
import org.example.barriga.service.repositories.TransacaoDAO;

import java.time.LocalDateTime;

public class TransacaoService {

    private TransacaoDAO dao;

    private ClockService clockService;

    public Transacao salvar (Transacao transacao) {
//        if (getTime().getHour() > 17) {
//            // Same implementation below
//        }

        if(clockService.getCurrentDateTime().getHour() > 17) {
            throw new RuntimeException("Transações não são permitidas nesse horário");
        }

        if(transacao.getDescricao() == null) throw new ValidationException("Descrição é obrigatório");
        if(transacao.getValor() == null) throw new ValidationException("Valor é obrigatório");
        if(transacao.getConta() == null) throw new ValidationException("Conta é obrigatório");
        if(transacao.getData() == null) throw new ValidationException("Data é obrigatório");
        if(transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }

//    protected LocalDateTime getTime() {
//        return LocalDateTime.now();
//    }
}
