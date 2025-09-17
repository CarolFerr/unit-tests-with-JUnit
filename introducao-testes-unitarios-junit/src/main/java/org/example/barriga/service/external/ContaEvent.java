package org.example.barriga.service.external;

import org.example.barriga.domain.Conta;

public interface ContaEvent {

    public enum EventType {
        CREATED,
        UPDATED,
        DELETED;
    }

    void dispatch(Conta conta, EventType type) throws Exception;
}
