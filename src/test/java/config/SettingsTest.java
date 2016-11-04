package config;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Works only with default config.properties.
 * If need to test, change current config.properties name and let program
 * generate new one.
 *
 * Created by Geit on 04.11.2016.
 */
public class SettingsTest {
    private static Settings settings;
    @BeforeClass
    public static void setUp() throws Exception {
        settings = new Settings();
    }

    @Test
    public void getDatabase() throws Exception {
        assertEquals("localhost", settings.getDatabase());
    }

    @Test
    public void getPort() throws Exception {
        assertEquals(5432, (int)settings.getPort());
    }

    @Test
    public void getDBUser() throws Exception {
        assertEquals("postgres", settings.getDBUser());
    }

    @Test
    public void getDBPassword() throws Exception {
        assertEquals("password", settings.getDBPassword());
    }

    @Test
    public void getConnectionUrl() throws Exception {
        assertEquals("jdbc:postgresql://localhost:5432/", settings.getConnectionUrl());
    }

}