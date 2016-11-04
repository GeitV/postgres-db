package database.connection;

import config.Settings;
import data.JSONData;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

/**
 * Created by Geit on 04.11.2016.
 */
@RestController
@RequestMapping("/data")
public class CommunicationController {

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void addToTable(@RequestBody JSONData data) throws SQLException {
        queryExecutor("INSERT INTO \"data\" (data) values ('" + data.getData() + "');", false);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public JSONArray findFromTable(@RequestParam String search) throws SQLException {
        JSONArray array = queryExecutor("SELECT data FROM data WHERE data LIKE '%" + search + "%';", true);
        return array;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public JSONArray removeFromTable(@RequestParam String remove) throws SQLException {
        JSONArray array = queryExecutor("DELETE FROM data WHERE data = '" + remove + "';", false);
        return array;
    }

    private JSONArray queryExecutor(String query, boolean isQuery) throws SQLException {
        JSONArray array = new JSONArray();
        Connection connection = DriverManager.getConnection(Settings.getConnectionUrl(), Settings.getDBUser(),
                Settings.getDBPassword());
        Statement statement = connection.createStatement();

        if (isQuery) {
            ResultSet resultSet = statement.executeQuery(query);
            array = new JSONArray();
            while (resultSet.next()) {
                array.put(resultSet.getString("data"));
            }
            resultSet.close();
        } else {
            statement.executeUpdate(query);
        }
        statement.close();
        connection.close();

        return array;
    }
}
