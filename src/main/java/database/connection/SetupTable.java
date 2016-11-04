package database.connection;

import config.Settings;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Created by Geit on 03.11.2016.
 */
@SpringBootApplication
public class SetupTable {
    public static void main(String[] args) {
        Settings settings = new Settings();

        SpringApplication.run(SetupTable.class, args);
        Flyway flyway = new Flyway();
        flyway.setDataSource(settings.getConnectionUrl(), settings.getDBUser(), settings.getDBPassword());
        flyway.migrate();
    }
}
