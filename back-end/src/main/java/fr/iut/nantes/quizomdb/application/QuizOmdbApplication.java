package fr.iut.nantes.quizomdb.application;

import fr.iut.nantes.quizomdb.controler.ControlerGeneral;
import fr.iut.nantes.quizomdb.entite.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Main class
 *
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@Controller
@CrossOrigin
@RequestMapping("/")
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
     *
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

    @GetMapping(value = "/question",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity generateQuestion(@RequestParam("token") String token) {
        ResponseEntity res = ResponseEntity.ok(control.generateQuestion(token));
        return res;
    }

    @PostMapping(value = "/response",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity sendResponse(@RequestParam("token") String token, @RequestParam("response") String response) {
        ResponseEntity res = ResponseEntity.ok("{ " +
                "\"result\" : \"" + control.isCorrectResponse(token, response)
                + "\" }");
        return res;
    }

    @PostMapping(value = "/login",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity login(@RequestParam("login") String login, @RequestParam("password") String password) {
        ResponseEntity res = ResponseEntity.ok("{ " +
                "\"token\" : \"" + control.login(login, password)
                + "\" }");
        return res;
    }

    @PostMapping(value = "/disconnect",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity disconnect(@RequestParam("token") String token) {
        control.disconnect(token);
        ResponseEntity res = ResponseEntity.ok("{ \"result\" : \"ok\" }");
        return res;
    }

    @GetMapping(value = "/goodanswers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getGoodAnswers(@RequestParam("token") String token) {
        ResponseEntity res = ResponseEntity.ok("{ " +
                "\"goodanswers\" : \"" + control.getGoodAnswers(token)
                + "\" }");
        return res;
    }

    @GetMapping(value = "/answers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getAnswers(@RequestParam("token") String token) {
        ResponseEntity res = ResponseEntity.ok("{ " +
                "\"answers\" : \"" + control.getAnswers(token)
                + "\" }");
        return res;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "<h1>Welcome !</h1>";
    }

    @PostMapping(value = "/register",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity register(@RequestParam("login") String login, @RequestParam("password") String password) {
        ResponseEntity res = ResponseEntity.ok(control.createAccount(login, password));
        return res;
    }

}
