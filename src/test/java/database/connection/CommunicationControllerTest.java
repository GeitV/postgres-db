package database.connection;

import config.Settings;
import data.JSONData;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Geit on 04.11.2016.
 */
public class CommunicationControllerTest {
    private static CommunicationController controller;

    @BeforeClass
    public static void classSetup() throws Exception {
        Settings settings = new Settings();
        controller = new CommunicationController();
    }

    @After
    public void destroy() throws SQLException {
        controller.removeFromTable("My-super-secret-data");
    }

    @Test
    public void addToTableAndFindIt() throws SQLException {
        JSONData data = new JSONData();
        data.setData("My-super-secret-data");
        controller.addToTable(data);
        assertTrue(controller.findFromTable("My-super-secret-data").length() > 0);
    }

    @Test
    public void removeFromTable() throws SQLException {
        JSONData data = new JSONData();
        data.setData("My-super-secret-data");
        controller.addToTable(data);
        controller.removeFromTable("My-super-secret-data");
        assertEquals(0, controller.findFromTable("My-super-secret-data").length());
    }
}