package data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Geit on 04.11.2016.
 */
public class JSONDataTest {
    JSONData dataFile = new JSONData();

    @Test
    public void setAndGetData() throws Exception {
        dataFile.setData("Data");
        assertEquals("Data", dataFile.getData());
    }
}