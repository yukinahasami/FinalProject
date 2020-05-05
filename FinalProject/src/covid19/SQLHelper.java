
/**
 * CMPSC 221 Final Project#
 * SQLHelper.java
 * Purpose: The SQL Helper is working on calculating the confirmed, 
 * recovered, and deaths population during a selected period. 
 * Also, it will connect to the database and return data.
 * @author Yuxin Deng
 */
package covid19;
import java.sql.*;
import java.io.*;
import java.util.*;




public class SQLHelper {
    
    /*
    *Calculate accumulative data in a country. 
    *There are three switch cases for Deaths, Recovered, and Confiremed
    *@param int mode
    *@param String startingDate
    *@param String endingDate
    *@param String country
    *@return String return
    */
    public static String AccumulatedData(int mode, String country,String startingDate,String endingDate){
       
        //create instance variable
        String className = null;
        String url=null;
        String user = null;
        String password = null;
        
        int total = 0;
        String totalStr = "";
        
        try
        {
            ResourceBundle resources;
            InputStream in = null;
            ResourceBundle newResources;

            //in = ClassLoader.getSystemResourceAsStream("db.properties");

            //resources = new PropertyResourceBundle(in);

            //in.close();

            className = "org.apache.derby.jdbc.ClientDriver";//resources.getString("jdbc.driver");
            url = "jdbc:derby://localhost:1527/CovidDatabase";//resources.getString("jdbc.url");
            System.out.println(url);
            user = "APP";//resources.getString("jdbc.user");
            password = "APP";//resources.getString("jdbc.password");
        }
        catch (Exception exp)
        {
            System.out.println("Couldn't load resources." + exp);
            System.exit(-1);
        }
        
        try
        {
            Class.forName(className);
        }
        catch (Exception e) 
        {
            totalStr = ("Failed to load  driver.");
        }
        
        try
        {
            
            Connection con = DriverManager.getConnection(url,user,password);
                 Statement stmt = con.createStatement();  
                 Statement stmt2 = con.createStatement();
            switch(mode){
                case 1:// Calculate Confiremed population during the selected period.
                    ResultSet result1 = stmt.executeQuery("Select sum(CONFIRMED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + endingDate + "'"); 
                    ResultSet result2 = stmt2.executeQuery("Select sum(CONFIRMED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + startingDate + "'"); 
                    
                    
           
                    if(result1.next() && result2.next()){
                       
                        
                        total = Integer.parseInt(result1.getString(1)) - Integer.parseInt(result2.getString(1));
                    }
                    else{
                        totalStr = ("Please input correct country name");
                    }
                    break;
                    
                case 2:
                    ResultSet result3 = stmt.executeQuery("Select sum(RECOVERED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + endingDate + "'"); 
                    ResultSet result4 = stmt2.executeQuery("Select sum(RECOVERED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + startingDate + "'"); 
           
                    if(result3.next() && result4.next()){
                        
                        total = result3.getInt(1) - result4.getInt(1);
                    }
                    else{
                        
                        totalStr = ("Please input correct country name");
                    }
                    break;
                    
                case 3:
                    ResultSet result5 = stmt.executeQuery("Select sum(DEATHS) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + endingDate + "'"); 
                    ResultSet result6 = stmt2.executeQuery("Select sum(DEATHS) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE = '" + startingDate + "'"); 
           
                    if(result5.next() && result6.next()){
                   
                        total = result5.getInt(1) - result6.getInt(1);
                    }
                    else{
                        System.out.print("Please input correct country name");
                    }
                    break;
            }
              
               con.close(); 
        }
         
        
            catch (SQLException e) {
            System.out.println(e);
        }
            
            totalStr = Integer.toString(total);
        
            return totalStr;
   
        }
}
        
        
    
    



/*
expected use cases
1 - confirmed- Country - month
2 - recovered - country - month
3 - deaths - country - month
4 - confirmed - state - month
*/