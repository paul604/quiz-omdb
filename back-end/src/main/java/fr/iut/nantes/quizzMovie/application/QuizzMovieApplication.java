package fr.iut.nantes.quizzMovie.application;

import fr.iut.nantes.quizzMovie.controler.ControlerGeneral;
import fr.iut.nantes.quizzMovie.entite.Config;
import fr.iut.nantes.quizzMovie.entite.TokenException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

import javax.servlet.http.HttpServletRequest;

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
    ControlerGeneral control;

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
    ResponseEntity generateQuestion(HttpEntity<String> httpEntity) {
        try {
            String token = getJsonFromBody(httpEntity).get("token").getAsString();
            ResponseEntity res = ResponseEntity.ok(control.generateQuestion(token));
            return res;
        }catch (TokenException e) {
            return e.getResponseEntity();
        }catch (Exception e) {
            String json = "{ \"error\" : \" Error not defined\" }";
            return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/response",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity sendResponse(@RequestParam("response") String response, HttpEntity<String> httpEntity) {
        try {
            String token = getJsonFromBody(httpEntity).get("token").getAsString();
            ResponseEntity res = ResponseEntity.ok("{ " +
                    "\"result\" : \"" + control.isCorrectResponse(token, response)
                    + "\" }");
            return res;
        }catch (TokenException e) {
            return e.getResponseEntity();
        }catch (Exception e) {
            String json = "{ \"error\" : \" Error not defined\" }";
            return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/login",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity login(HttpEntity<String> httpEntity) {
        try {
            Gson gson = new Gson();
            JsonElement element = gson.fromJson (httpEntity.getBody(), JsonElement.class);
            JsonObject jsonObj = element.getAsJsonObject();
            String login = jsonObj.get("login").getAsString();
            String password = jsonObj.get("password").getAsString();

            ResponseEntity res = ResponseEntity.ok("{ " +
                    "\"token\" : \"" + control.login(login, password)
                    + "\" }");
            return res;
        } catch (Exception e) {
            String json = "{ \"error\" : \" Login or password invalid\" }";
            return new ResponseEntity<>(json, HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(value = "/disconnect",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity disconnect(HttpEntity<String> httpEntity) {
        try {
            String token = getJsonFromBody(httpEntity).get("token").getAsString();
            control.disconnect(token);
            ResponseEntity res = ResponseEntity.ok("{ \"result\" : \"ok\" }");
            return res;
        } catch (TokenException e) {
            return e.getResponseEntity();
        }
    }

    @GetMapping(value = "/goodanswers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getGoodAnswers(HttpEntity<String> httpEntity) {
        try {
            String token = getJsonFromBody(httpEntity).get("token").getAsString();
            ResponseEntity res = ResponseEntity.ok("{ " +
                    "\"goodanswers\" : \"" + control.getGoodAnswers(token)
                    + "\" }");
            return res;
        }catch (TokenException e) {
            return e.getResponseEntity();
        }
    }

    @GetMapping(value = "/answers",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    ResponseEntity getAnswers(HttpEntity<String> httpEntity) {
        try {
            String token = getJsonFromBody(httpEntity).get("token").getAsString();
            ResponseEntity res = ResponseEntity.ok("{ " +
                    "\"answers\" : \"" + control.getAnswers(token)
                    + "\" }");
            return res;
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
            JsonObject jsonObj = getJsonFromBody(httpEntity);
            String login = jsonObj.get("login").getAsString();
            String password = jsonObj.get("password").getAsString();
            ResponseEntity res = ResponseEntity.ok(control.createAccount(login, password));
            return res;
        } catch (Exception e) {
            String json = "{ \"error\" : \" login already used\" }";
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }
    }

    private JsonObject getJsonFromBody(HttpEntity<String> httpEntity){
        Gson gson = new Gson();
        JsonElement element = gson.fromJson (httpEntity.getBody(), JsonElement.class);
        return element.getAsJsonObject();
    }

}
