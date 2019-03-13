package ru.example.cinemaapi;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class MyTestcontainersTests {

    @Container
    private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Test
    void test() {
        assertTrue(postgresqlContainer.isRunning());


    }
}
