<%-- 
    Document   : results
    Created on : May 4, 2020, 3:04:31 AM
    Author     : Abby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COVID-19 Query Results</title>
    </head>
    <body>
        <h1></h1>
        <%
            String results = (String) request.getAttribute("results");
            String choice = (String) request.getAttribute("choice");
            String months = (String) request.getAttribute("months");
            String country = (String) request.getAttribute("country");
            
            int monthsInt = Integer.parseInt(months);
            int choiceInt = Integer.parseInt(choice);
            
            String choicePrint = "";
            String monthName = "";
            
            switch (choiceInt){
                case 1:
                    choicePrint = " confirmed cases.";
                    break;
                    
                case 2:
                    choicePrint = " recovered cases.";
                    break;
                    
                case 3:
                    choicePrint = " deaths.";
                    break;
            }
            
            switch (monthsInt){
                case 1:
                    monthName = "January (1/22 - 1/31)";
                    break;
                case 2:
                    monthName = "February";
                    break;
                case 3:
                    monthName = "March";
                    break;
                case 4:
                    monthName = "April";
                    break;
            }
            
            
            
            out.print("In "+ country + " during the month of " + monthName + " there were " + results + choicePrint);
            
            
            
            

            %>
        
    </body>
</html>
