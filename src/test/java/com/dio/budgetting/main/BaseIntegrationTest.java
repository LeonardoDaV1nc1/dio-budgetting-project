package com.dio.budgetting.main;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class BaseIntegrationTest {
    // Aqui você pode colocar configurações comuns para todos os testes se quiser
}
