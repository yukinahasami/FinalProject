/**
 * CMPSC 221 Exercise *****#
 * SQLHelper.java
 * Purpose: Create a class to calculate the data we need to display main page.
 * 
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
        String total = "";
        
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
            System.out.println("Failed to load  driver.");
        }
        
        try
        {
            
            Connection con = DriverManager.getConnection(url,user,password);
                 Statement stmt = con.createStatement();  
                 
            switch(mode){
                
                case 1: // Calculate Confiremed population during the selected period.
                    ResultSet result1 = stmt.executeQuery("Select CONFIRMED "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ endingDate+ "';"); 
           
                    ResultSet result2 = stmt.executeQuery("Select CONFIRMED "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ startingDate+ "';"); 
                    
                    if(result1.next()&& result2.next()){
            
                        total = Integer.toString(result1.getInt(1) - result2.getInt(1));
                        
                    }
                    else{
                        System.out.print("Please input correct country name");
                    }
                    break;
                    
                case 2: // Calculate RECOVERED population during the selected period.
                   ResultSet result3 = stmt.executeQuery("Select RECOVERED "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ endingDate+ "';"); 
                    
                   ResultSet result4 = stmt.executeQuery("Select RECOVERED "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ startingDate+ "';"); 
                    
                    if(result3.next()&& result4.next()){
                   
                        total = Integer.toString(result3.getInt(1)- result4.getInt(1));
                    }
                    else{
                        
                        System.out.print("Please input correct country name");
                    }
                    break;
                    
                case 3: // Calculate Deaths population during the selected period.
                    ResultSet result5 = stmt.executeQuery("Select DEATHS "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ endingDate+ "';"); 
                    ResultSet result6 = stmt.executeQuery("Select DEATHS "
                            + "from CORONAVIRUSCOUNTRIES where country = '"
                            +country + "' and Date = '"+ startingDate+ "';"); 
           
                    if(result5.next() && result6.next()){
                  
                        total = Integer.toString(result5.getInt(1) - result6.getInt(1));
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
            return total;
   
        }
}
        
        
    
    



/*
expected use cases
1 - confirmed- Country - month
2 - recovered - country - month
3 - deaths - country - month
4 - confirmed - state - month

*/