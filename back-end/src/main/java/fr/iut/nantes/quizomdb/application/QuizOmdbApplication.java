package fr.iut.nantes.quizomdb.application;

import fr.iut.nantes.quizomdb.controler.ControlerGeneral;
import fr.iut.nantes.quizomdb.entite.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main class
 *
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@Controller
public class QuizOmdbApplication extends SpringBootServletInitializer {

    /**
     * log of application
     *
     * @since 1.0
     */
    public static final Logger log = LoggerFactory.getLogger("QuizOmdb");
    public static Config config;
    ControlerGeneral control;

    public QuizOmdbApplication() {
        if (config == null) {
            String configPath = QuizOmdbApplication.class.getResource("/config.properties").getPath();
            log.info("path of config file: " + configPath);
            config = new Config(configPath);
        }
        control = new ControlerGeneral();
    }

    /**
     * @param args argument of application
     * @since 1.0
     */
    public static void main(String[] args) {
        String configPath = args.length > 0 ?
                QuizOmdbApplication.class.getResource(args[0]).getPath() :
                QuizOmdbApplication.class.getResource("/config.properties").getPath();
        log.info("path of config file: " + configPath);
        config = new Config(configPath);
        SpringApplication.run(QuizOmdbApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QuizOmdbApplication.class);
    }

    @CrossOrigin
    @RequestMapping("/question")
    @ResponseBody
    String generateQuestion(String token) {
        return control.generateQuestion(token);
    }

    @CrossOrigin
    @RequestMapping("/response")
    @ResponseBody
    String sendResponse(String token, @RequestParam("response") String response) {

        return "{ " +
                "\"result\" : \"" + control.isCorrectResponse(token, response)
                + "\" }";
    }

    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    String login(String login, String password) {
        return "{ " +
                "\"token\" : \"" + control.login(login, password)
                + "\" }";
    }

    @CrossOrigin
    @RequestMapping("/disconnect")
    @ResponseBody
    String disconnect(String token) {
        control.disconnect(token);
        return "You have been disconnected.";
    }

    @CrossOrigin
    @RequestMapping("/goodanswers")
    @ResponseBody
    String getGoodAnswers(String token) {
        return "Number of good answers : " + control.getGoodAnswers(token);
    }

    @CrossOrigin
    @RequestMapping("/answers")
    @ResponseBody
    String getAnswers(String token) {
        return "Number of answers : " + control.getAnswers(token);
    }

    @CrossOrigin
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "<h1>Welcome !</h1>";
    }

    @CrossOrigin
    @RequestMapping("/register")
    @ResponseBody
    String register() {
        return "<h1>Inscription page</h1>";
    }

    @CrossOrigin
    @RequestMapping("/quizz")
    @ResponseBody
    String quizz() {
        return "<h1>Quizz page</h1>";
    }

    @CrossOrigin
    @RequestMapping("/createaccount")
    @ResponseBody
    String createAccount(@RequestParam("login") String login, @RequestParam("password") String password) {

        return control.createAccount(login, password);
    }



}
