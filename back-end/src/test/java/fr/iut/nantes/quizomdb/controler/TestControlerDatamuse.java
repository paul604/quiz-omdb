package fr.iut.nantes.quizomdb.controler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 1.0
 * @see ControlerDatamuse
 * @since 1.0
 */
public class TestControlerDatamuse {

    ControlerDatamuse control;

    /**
     * @since 1.0
     */
    @Before
    public void initialize() {
        control = new ControlerDatamuse();
    }

    /**
     * @throws Exception
     * @see ControlerDatamuse#hasCloseSpelling(String, String)
     * @since 1.0
     */
    @Test
    public void hasCloseSpelling1() throws Exception {
        String response = "response", answers = "response";
        assertTrue(control.hasCloseSpelling(response, answers));

    }

    /**
     * @throws Exception
     * @see ControlerDatamuse#hasCloseSpelling(String, String)
     * @since 1.0
     */
    @Test
    public void hasCloseSpelling2() throws Exception {
        String response = "respons", answers = "response";
        assertFalse(control.hasCloseSpelling(response, answers));

    }


    /**
     * @throws Exception
     * @see ControlerDatamuse#hasCloseSpelling(String, String)
     * @since 1.0
     */
    @Test
    public void hasCloseSpelling3() throws Exception {
        String response = "response answers", answers = "response response";
        assertFalse(control.hasCloseSpelling(response, answers));

    }


    /**
     * @throws Exception
     * @see ControlerDatamuse#hasCloseSpelling(String, String)
     * @since 1.0
     */
    @Test
    public void hasCloseSpelling4() throws Exception {
        String response = "legend", answers = "lagend";
        assertTrue(control.hasCloseSpelling(response, answers));

    }
}