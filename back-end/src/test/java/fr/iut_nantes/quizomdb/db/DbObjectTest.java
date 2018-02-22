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
}