package fr.iut_nantes.quizomdb.db;

import fr.iut_nantes.quizomdb.entite.Gamer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @see fr.iut_nantes.quizomdb.db.DbObject
 * @since 1.0
 */
public class DbObjectTest {

    private Idb db;

    /**
     * init db whit {@link DbObject}
     *
     * @throws Exception
     * @since 1.0
     */
    @Before
    public void setUp() throws Exception {
        db = new DbObject();
    }

    /**
     * test if after init, list of gamer is empty
     *
     * @throws Exception
     * @since 1.0
     */
    @Test
    public void listGamerIsEmpty() throws Exception {
        assertTrue("list Gamer Is not Empty", db.getAllGamer().isEmpty());
    }

    /**
     * add gamer whit {@link DbObject#addGamer(Gamer)}
     *
     * @throws Exception
     * @since 1.0
     */
    @Test
    public void addGamerDirectly() throws Exception {
        Gamer gamer = new Gamer("test", "pwdTest");
        db.addGamer(gamer);
        assertFalse("List gamers is empty", db.getAllGamer().isEmpty());
        assertEquals("Object gamer is not equals", gamer, db.getGamer(gamer.getLogin()));
    }

    /**
     * add gamer whit {@link DbObject#addGamer(String, String)}
     *
     * @throws Exception
     * @since 1.0
     */
    @Test
    public void addGamerIndirectly() throws Exception {
        String name = "test";
        Gamer gamer = new Gamer(name, "pwdTest");
        db.addGamer(name, "pwdTest");
        assertFalse("List gamers is empty", db.getAllGamer().isEmpty());
        assertEquals("Object gamer is not equals", gamer, db.getGamer(name));
    }
}