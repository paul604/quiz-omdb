package fr.iut.nantes.quizomdb.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main class
 *
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@Controller
public class QuizOmdbApplication {

    /**
     * log of application
     *
     * @since 1.0
     */
    public static final Logger log = LoggerFactory.getLogger("QuizOmdb");

    /**
     * @param args argument of application
     * @since 1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(QuizOmdbApplication.class, args);
    }



    // TODO method that have meaning...
    @RequestMapping("/bonjour")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/bonjour2")
    @ResponseBody
    String home2() {
        return "NNOOOON!";
    }



}
