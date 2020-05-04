/**
 * CMPSC 221 Exercise *****#
 * SQLHelper.java
 * Purpose: ****
 * 
 * @author Yuxin Deng
 */
package covid19;
import java.sql.*;
import java.io.*;
import java.util.*;




public class SQLHelper {
    
    /*
    *Calculate accumulative data in a country
    *@param String startingDate
    *@param int mode
    *@param String endingDate
    *@param String country
    */
    public static int AccumulatedData(int mode, String country,String startingDate,String endingDate){
       
        //create instance variable
        String className = null;
        String url=null;
        String user = null;
        String password = null;
        int total = 0;
        
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
                case 1:
                    ResultSet result1 = stmt.executeQuery("Select sum(CONFIRMED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE >= '" + startingDate + "' and Date <= '" + endingDate + "'"); 
           
                    if(result1.next()){
                   
                        System.out.println(result1.getInt(1));
                        total = result1.getInt(1);
                    }
                    else{
                        System.out.print("Please input correct country name");
                    }
                    break;
                    
                case 2:
                    ResultSet result2 = stmt.executeQuery("Select sum(RECOVERED) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE >= '" + startingDate + "' and Date <= '" + endingDate + "'"); 
           
                    if(result2.next()){
                   
                        System.out.println(result2.getInt(1));
                        total = result2.getInt(1);
                    }
                    else{
                        
                        System.out.print("Please input correct country name");
                    }
                    break;
                    
                case 3:
                    ResultSet result3 = stmt.executeQuery("Select sum(DEATHS) from CORONAVIRUSCOUNTRIES where COUNTRY ='" 
                    + country + "'and  DATE >= '" + startingDate + "' and Date <= '" + endingDate + "'"); 
           
                    if(result3.next()){
                   
                        System.out.println(result3.getInt(1));
                        total = result3.getInt(1);
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