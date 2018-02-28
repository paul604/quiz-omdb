package fr.iut.nantes.quizomdb.controler;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by E155722N on 28/02/18.
 */
public class ControlerDatamuse {


    /**
     * Check if response has a spelling close to answers
     *
     * @param response proposed
     * @param answers  that we want
     * @return true or false
     */
    public boolean hasCloseSpelling(String response, String answers) {
        String[] responseWords = response.split(" ");
        String[] responseAnswers = answers.split(" ");

        if (responseWords.length == responseAnswers.length) return false;
        for (int i = 0; i < responseAnswers.length; i++) {
            if (!this.compareWithDatamuse(responseWords[i], responseAnswers[i])) return false;
        }
        return true;
    }

    /**
     * Check if the 2 words in parameter has a close spelling
     *
     * @param responseWord
     * @param answersWord
     * @return true of false
     */
    private boolean compareWithDatamuse(String responseWord, String answersWord) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        try {
            JsonArray possibilities = gson.fromJson(this.getFromDatamuse(answersWord), JsonObject.class).getAsJsonArray();
            for (JsonElement json : possibilities) {
                String possibility = json.getAsJsonObject().get("word").toString();
                if (possibility.equalsIgnoreCase(responseWord)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get from the api of datamuse word that have a close spelling
     *
     * @param word that we want to have the spelling words close.
     * @return array of json
     * @throws Exception
     */
    private String getFromDatamuse(String word) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://api.datamuse.com/words?sp=" + word);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
