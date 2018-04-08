
package commisioncalc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author grich
 */
public class TestingJson {
    // Testing the JSONSimple library before implementing it in the main application
    
    public static void main(String args[]){
        JSONObject testPerson = new JSONObject();
        JSONArray salesData = new JSONArray();
        JSONArray data = new JSONArray(); // 0 = date, 1 = sale amount
        
        testPerson.put("id", 0);
        testPerson.put("name", "hugh");
        data.add("05-11-2017");
        data.add("105.00");
        
        salesData.add(data);
        
        
        testPerson.put("salesData",salesData);
        
        String output = (String) ((JSONArray)((JSONArray)testPerson.get("salesData")).get(0)).get(1);
        
        System.out.println(output);
    }
}
