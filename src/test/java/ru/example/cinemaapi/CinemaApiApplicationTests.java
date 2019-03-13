package ru.example.cinemaapi;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.example.cinemaapi.model.MovieSession;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = {CinemaApiApplicationTests.Initializer.class})
public class CinemaApiApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        // ??
    }

    @Test
    @Sql("/single-movie.sql")
    public void testSingleMovieTimetable() {

        // arrange

        //act
        ResponseEntity<List<MovieSession>> response = restTemplate.exchange("/timetable", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MovieSession>>(){});

        List<MovieSession> sessions = response.getBody();

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sessions.size()).isEqualTo(1);

        assertThat(sessions.get(0).getMovieName()).isEqualTo("Terminator");
        assertThat(sessions.get(0).getDuration()).isEqualTo("1:30");
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword(),
                    "spring.datasource.initialization-mode=always"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
