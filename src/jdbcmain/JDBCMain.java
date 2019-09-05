/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcmain;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdbcmain.DBConnection.conn;

/**
 *
 * @author usharifzoda
 */
public class JDBCMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int employeeID;
        String employeeName, department, hireDate;
        
        // Create Scanner Object
        Scanner keyboard = new Scanner(System.in);
        
        // Get input from user
//        System.out.println("Enter employee id: ");
//        employeeID = keyboard.nextInt();
        
//        System.out.print("Enter Employee Name: ");
//        employeeName = keyboard.nextLine();
//
//        System.out.print("Enter Department: ");
//        department = keyboard.nextLine();
//        
//        System.out.print("Enter Hire Date (yyyy-mm-dd HH:MM:SS): ");
//        hireDate = keyboard.nextLine();
//        
        
        try {
            DBConnection.makeConnection();
            
            // Create Statement Object 
            Statement stmt = conn.createStatement();
            
            // Writy SQL Query
//            String sqlStatement = "INSERT INTO employee_tbl(EmployeeName, Department, HireDate)" + 
//                    "VALUES(" + 
//                    "'" + employeeName + "'" + "," + 
//                    "'" + department + "'" + "," + 
//                    "'" + hireDate + "'" +
//                            ")";
//            
//            // Execute INSERT Statement
//            stmt.executeUpdate(sqlStatement);
            
            String deleteStatement = "DELETE FROM employee_tbl WHERE EmployeeID = " + String.valueOf(9);
                                

      //          stmt.executeUpdate(deleteStatement);
            Query.makeQuery(deleteStatement);

            // Write SQL Statement 
            String sqlStatement = "SELECT * FROM employee_tbl";
            Query.makeQuery(sqlStatement);
            // Execute Statement and Create Result Set Object
//            ResultSet result = stmt.executeQuery(sqlStatement);
            ResultSet result = Query.getResult();
            // Get all records from ResultSet object
            
            while(result.next()){
                System.out.print(result.getInt("EmployeeID") + " | ");
                System.out.print(result.getString("EmployeeName") + " | ");
                System.out.print(result.getString("Department") + " | ");
                System.out.print(result.getDate("HireDate") + " ");
                System.out.println(result.getTime("HireDate") + "\n");
            }
            
            
            launch(args);
            DBConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
