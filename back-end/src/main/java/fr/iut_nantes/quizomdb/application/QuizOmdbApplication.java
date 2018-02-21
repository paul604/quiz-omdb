package fr.iut_nantes.quizomdb.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class
 *
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class QuizOmdbApplication {

    /**
     * log of application
     *
     * @since 1.0
     */
    public static Logger log = LoggerFactory.getLogger("QuizOmdb");

    /**
     * @param args argument of application
     * @since 1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(QuizOmdbApplication.class, args);
    }


}
