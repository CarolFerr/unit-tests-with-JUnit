package org.example.barriga.suite;


import org.example.barriga.domain.UsuarioTest;
import org.example.barriga.service.UsuarioServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite de Testes")
@SelectClasses(value = {
        UsuarioTest.class,
        UsuarioServiceTest.class,
})

public class SuiteTest {

}
