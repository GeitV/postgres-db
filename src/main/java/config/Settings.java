package config;

import java.io.*;
import java.util.Properties;

/**
 * Created by Geit on 04.11.2016.
 */
public class Settings {
    private static final String[] DEFAULT_PARAMS = {"localhost", "5432", "postgres", "password"};
    private static String database;
    private static Integer port;
    private static String dbuser;
    private static String dbpassword;

    public Settings() {
        String[] params = DEFAULT_PARAMS;
        try {
            params = setParams();
        } catch (FileNotFoundException ex) {
            createConfigFile();
            System.err.println("URGENT: Config file (config.properties) has not been set, closing down app...");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        database = params[0];
        port = Integer.parseInt(params[1]);
        dbuser = params[2];
        dbpassword = params[3];
    }

    public static String getDatabase() { return database; }

    public static Integer getPort() { return port; }

    public static String getDBUser() { return dbuser; }

    public static String getDBPassword() { return dbpassword; }

    public static String getConnectionUrl() {
        return "jdbc:postgresql://" + database + ":" + port + "/";
    }

    private void createConfigFile() {
        try {
            OutputStream output = new FileOutputStream("config.properties");

            Properties properties = new Properties();
            properties.setProperty("database", DEFAULT_PARAMS[0]);
            properties.setProperty("port", DEFAULT_PARAMS[1]);
            properties.setProperty("dbuser", DEFAULT_PARAMS[2]);
            properties.setProperty("dbpassword", DEFAULT_PARAMS[3]);

            properties.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] setParams() throws IOException {
        Properties properties = new Properties();
        String[] params = new String[4];

        InputStream input = new FileInputStream("config.properties");
        properties.load(input);
        input.close();

        params[0] = properties.getProperty("database");
        params[1] = properties.getProperty("port");
        params[2] = properties.getProperty("dbuser");
        params[3] = properties.getProperty("dbpassword");

        return params;
    }
}
