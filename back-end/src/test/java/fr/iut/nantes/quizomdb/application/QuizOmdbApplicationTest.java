package fr.iut.nantes.quizomdb.application;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.iut.nantes.quizomdb.Utils;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = QuizOmdbApplication.class)
@ComponentScan(basePackages = "fr.iut.nantes.quizomdb")
@AutoConfigureMockMvc
public class QuizOmdbApplicationTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @Before
    public void setUp() throws Exception {
        //decom if data clear
//        mvc.perform(MockMvcRequestBuilders.post("/register")
//                .param("login", "loginTest")
//                .param("password", "pwdLoginTest"));
        Utils.setupConfig();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/login")
                .param("login", "loginTest")
                .param("password", "pwdLoginTest")).andReturn();
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(result.getResponse().getContentAsString());
        token = parse.getAsJsonObject().get("token").getAsString();
    }

    @Test
    public void generateQuestion() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question")
                    .param("token", token))
                .andExpect( MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.question").isNotEmpty());
    }

    @Test
    public void sendResponseWhitNoQuestion() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/response"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void sendResponse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question")
                .param("token", token))
                .andExpect( MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.question").isNotEmpty());
        mvc.perform(MockMvcRequestBuilders.post("/response")
                .param("token", token)
                .param("response", "................"))
                .andExpect( MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void disconnect() throws Exception {
    }

    @Test
    public void getGoodAnswers() throws Exception {
    }

    @Test
    public void getAnswers() throws Exception {
    }

    @Test
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.server").value("OK"));
    }

    @Test
    public void register() throws Exception {
    }


}