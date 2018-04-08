/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commisioncalc;

import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
/**
 *
 * @author grich
 */
public class LoadSave {
    private File fullSalesDataFile = new File("datafile.json");
    
    public LoadSave(){
        try {
            fullSalesDataFile.createNewFile(); // if a file doesn't exist, create a new file
        }  catch(IOException e){
            
        }
    }
    
    public ArrayList<Person> getPeople(){
        // Gets data from file using getData() and iterates through populating an ArrayList<Person> using the given data to instantiate each Person(JSONObject)
        
        JSONArray data = getData();
        
        int dataLength = data.size();
        ArrayList<Person> people = new ArrayList<>();
        
        for(int i = 0;i < dataLength; i++){
            people.add(i, new Person((JSONObject)data.get(i)));
        }
        
        return people;
    }
    
    private JSONArray getData(){
        // Parse JSON string from loaded file and return data as a JSONArray (used by getPeople())
        
        JSONArray salesDataArray = new JSONArray(); 
        
        try {
            JSONParser parser = new JSONParser();
            
            salesDataArray = (JSONArray) parser.parse(new FileReader(fullSalesDataFile));

            
        } catch(ParseException p){
            
        } catch(IOException e){
            
        }
        
        if(salesDataArray.isEmpty()){
            JSONObject testPerson = new JSONObject();
            JSONArray salesData = new JSONArray();
            JSONObject data = new JSONObject(); // 0 = date, 1 = sale amount
        
            testPerson.put("id", 0);
            testPerson.put("name", "hugh");
            data.put("date","05-11-2017");
            data.put("value","105.00");

            salesData.add(data);


            testPerson.put("salesData",salesData);
            
            salesDataArray.add(testPerson);
        }
        
        return salesDataArray;
        
    }
    
    public void saveData(JSONArray people){
        try {
            FileWriter file = new FileWriter(fullSalesDataFile); // Writes JSON string to file
            String string = people.toJSONString();
            file.write(people.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
