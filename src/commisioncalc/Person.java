/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commisioncalc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.json.simple.*;

/* 
 * Description:
 *
 * 
 *  Author: George Richards
 *  Email: gbrichards1996@gmail.com
 *  Date: 26-09-2017
 *  
 */
public class Person {

    private int m_id;
    private String m_name;
    private HashMap<String,Float> m_salesData = new HashMap<>();
    
    public Person(JSONObject initialData){
        /* Importing initialData from a JSON entry used by LoadSave.getPeople() */
        JSONArray salesData;
        salesData = (JSONArray) initialData.get("salesData");
        
        m_id = (int) (Integer.parseInt(String.valueOf(initialData.get("id"))));
        m_name = (String) initialData.get("name");
        
        importSalesData(salesData);
    } //Constructor with data importing from a JSONObject
    
    public Person(int id, String name, float totalSales){
        //Creating salesperson from add new salesperson dialog, assuming totalsales counts for today
        m_id = id;
        m_name = name;
        
        m_salesData.put(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), totalSales);
    } // Constructor for a new Person created using the new salesperson dialog
    
    public int getId() {
        return m_id;
    }

    public void setId(int m_id) {
        this.m_id = m_id;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    } // Changes the record for the Person's name
    
    public void updateSale(String date, float newValue){
        m_salesData.put(date, newValue);
    } // Updates a given record if it exists
    
    public boolean addSale(String date, float newValue){
        return (m_salesData.putIfAbsent(date, newValue) == null); // return true(successfull) if no key (date) is already set
    } // Adds a new sale
    
    public float calculateBonus(){
        return calculateBonus(getTotalSales());
    } // Calculates bonus amount for the commission for the current sales data
    
    public float calculateBonus(float totalSales){
        int percentage = calculateCommission(totalSales);
        
        return (totalSales * (percentage / 100.0f));
    } // Calculates a bonus amount for the given value
    
    public int calculateCommission(){
        return calculateCommission(getTotalSales());
    } // Calculates commission percentage using current sales data total
    
    public int calculateCommission(float totalSales){
        /*  Commission is as follows: 
         *   £0 < totalSales <= 19,999 = 0%
         *   £19,999 < totalSales <= 30,999 = 5%
         *   £30,999 < totalSales <= 45,999 = 10%
         *   £45,999 < totalSales <= 59,999 = 12%
         *   £59,999 < totalSales = 15%
         * 
         * Returning an int as value is only ever displayed, not used for calculations
         */
        
        if(totalSales > 59999.0f) return 15;
        if(totalSales > 45999.0f) return 12;
        if(totalSales > 30999.0f) return 10;
        if(totalSales > 19999.0f) return 5;
        
        return 0;
    } // Calculates commission percentage for a given value
    
    public JSONArray getSales(){
        JSONArray salesData = new JSONArray();
        
        for (Map.Entry<String, Float> entry : m_salesData.entrySet()) {
            JSONObject data = new JSONObject();
            String key = entry.getKey();
            float value = entry.getValue();
            
            data.put("date",key);
            data.put("value",value);
            
            salesData.add(data);
        }
        
        return salesData;
    }
    
    public void importSalesData(JSONArray salesData){
        int countSales;
        
        countSales = salesData.size();

        for(int i = 0; i < countSales; i++){
            String date = (String) ((JSONObject)salesData.get(i)).get("date");
            float value = (float) Float.parseFloat(String.valueOf(((JSONObject)salesData.get(i)).get("value")));
            m_salesData.put(date, value);
        }
    } // Imports sales data from a JSONArray and merges it with current sales data
    
    public void importSalesData(JSONArray salesData, boolean overwrite){
        int countSales;
        
        if(overwrite){ 
            m_salesData = null;
            m_salesData = new JSONObject();
        }
        
        countSales = salesData.size();

        for(int i = 0; i < countSales; i++){
            String date = (String) ((JSONObject)salesData.get(i)).get("date");
            float value = (float) Float.parseFloat(String.valueOf(((JSONObject)salesData.get(i)).get("value")));
            m_salesData.put(date, value);
        }
    } // Imports sales data and deletes old sales data
    
    public float getTotalSales(){
        float totalSales = 0.0f;
        
        for (Map.Entry<String, Float> entry : m_salesData.entrySet()) {
            String key = entry.getKey();
            float value = entry.getValue();

            totalSales += value;
        }
        
        return totalSales;
    } // Returns the sum of all records in sales data

    public JSONObject exportToJSON(){
        JSONObject person = new JSONObject();
        JSONArray salesData = new JSONArray();
        
        for (Map.Entry<String, Float> entry : m_salesData.entrySet()) {
            JSONObject data = new JSONObject();
            String key = entry.getKey();
            float value = entry.getValue();
            
            data.put("date",key);
            data.put("value",value);
            
            salesData.add(data);
        }
        
        person.put("id", m_id);
        person.put("name", m_name);
        person.put("salesData", salesData);
        
        return person;
    } // Exports data to JSON string for saving in a text file
    
}
