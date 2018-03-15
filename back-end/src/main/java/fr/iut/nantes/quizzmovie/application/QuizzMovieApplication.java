package fr.iut.nantes.quizzmovie.application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.iut.nantes.quizzmovie.controler.ControlerGeneral;
import fr.iut.nantes.quizzmovie.entite.Config;
import fr.iut.nantes.quizzmovie.entite.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
public class QuizzMovieApplication extends SpringBootServletInitializer {

    /**
     * log of application
     *
     * @since 1.0
     */
    public static final Logger log = LoggerFactory.getLogger("QuizOmdb");
    public static Config config;

    private ControlerGeneral control;

    public QuizzMovieApplication() {
        if (config == null) {
            String configPath = QuizzMovieApplication.class.getResource("/config.properties").getPath();
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
                QuizzMovieApplication.class.getResource(args[0]).getPath() :
                QuizzMovieApplication.class.getResource("/config.properties").getPath();
        log.info("path of config file: " + configPath);
        config = new Config(configPath);
        SpringApplication.run(QuizzMovieApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QuizzMovieApplication.class);
    }

    @GetMapping(value = "/question",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity generateQuestion(@RequestParam String token) {
        try {
            return ResponseEntity.ok(control.generateQuestion(token));
        } catch (TokenException e) {
            return e.getResponseEntity();
        } catch (Exception e) {
            String json = "{ \"error\" : \" Error not defined\" }";
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/response",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity sendResponse(@RequestParam String token, HttpEntity<String> httpEntity) {
        try {
            String response = getParamFromBody(httpEntity, "response");
            return ResponseEntity.ok("{ " +
                    "\"result\" : \"" + control.isCorrectResponse(token, response)
                    + "\" }");
        } catch (TokenException e) {
            log.error("/response TokenException", e);
            return e.getResponseEntity();
        } catch (Exception e) {
            log.error("/response Exception", e);
            String json = "{ \"error\" : \" " + e.getMessage() + "\" }";
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity login(HttpEntity<String> httpEntity) {
        try {
            String login = getParamFromBody(httpEntity, "login");
            String password = getParamFromBody(httpEntity, "password");
            return ResponseEntity.ok("{ " +
                    "\"token\" : \"" + control.login(login, password)
                    + "\" }");
        } catch (Exception e) {
            log.error("exception /Login", e);
            String json = "{ \"error\" : \" Login or password invalid\" }";
            return new ResponseEntity<>(json, HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(value = "/disconnect",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity disconnect(@RequestParam String token) {
        try {
            control.disconnect(token);
            return ResponseEntity.ok("{ \"result\" : \"ok\" }");
        } catch (TokenException e) {
            return e.getResponseEntity();
        }
    }

    @GetMapping(value = "/goodanswers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getGoodAnswers(@RequestParam String token) {
        try {
            return ResponseEntity.ok("{ " +
                    "\"goodanswers\" : \"" + control.getGoodAnswers(token)
                    + "\" }");
        } catch (TokenException e) {
            return e.getResponseEntity();
        }
    }

    @GetMapping(value = "/answers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getanswers(@RequestParam String token) {
        try {
            return ResponseEntity.ok("{ " +
                    "\"answers\" : \"" + control.getAnswers(token)
                    + "\" }");
        } catch (TokenException e) {
            return e.getResponseEntity();
        }
    }

    @RequestMapping("/")
    @ResponseBody
    ResponseEntity index() {
        return ResponseEntity.ok("{ \"server\" : \"OK\" }");
    }

    @PostMapping(value = "/register",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity register(HttpEntity<String> httpEntity) {
        try {
            String login = getParamFromBody(httpEntity, "login");
            String password = getParamFromBody(httpEntity, "password");
            return ResponseEntity.ok(control.createAccount(login, password));
        } catch (Exception e) {
            String json = "{ \"error\" : \" login already used\" }";
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }
    }

    /**
     * @param httpEntity the request received
     * @param param      to find in body
     * @return the String value of the param
     */
    private String getParamFromBody(HttpEntity<String> httpEntity, String param) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(httpEntity.getBody(), JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        return jsonObj.get(param).getAsString();
    }

}
