/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

/**
 *
 * @author user
 */
import javaapplication8.Models.Employee;
import javaapplication8.Models.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Connection conn;
    public PreparedStatement preparedStatement;
    public ResultSet rs;
    private int id;
    private String name;
    private int qty;
    private int category_id;
    private int count;

    public Database() {
        initializedb();
    }
    
    public void initializedb(){
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://dbta.1ez.xyz/12_Shop?useSSL=false";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myURL, "MAR8906", "8b5y4rvm");

        } catch (Exception e) {
            System.err.println(e.toString());
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void AddEmployee(Employee e) {
        try {
            
            String query = "insert into Employee (NAME, GENDER, PhoneNumber, JoinDate, BranchID)" + "values(?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, e.Name);
            preparedStatement.setString(2, e.Gender );
            preparedStatement.setString(3, e.PhoneNumber);
            preparedStatement.setString(4, e.JoinDate);
            preparedStatement.setInt(5, e.BranchID);
            preparedStatement.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void AddProducts(String Name, int Price, String Description, int ProductTypeID) {
        try {
            
            String query = "insert into Products (Name, Price, Description, ProductTypeID)" + "values(?,?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, Name);
            preparedStatement.setInt(2, Price );
            preparedStatement.setString(3, Description);
            preparedStatement.setInt(4, ProductTypeID);
            preparedStatement.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void AddStock(int ProductID, int BranchID, int Stock){
        try {
            
            String query = "insert into ProductStock (ProductID, BranchID, Stock)" + "values(?,?,?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, ProductID);
            preparedStatement.setInt(2, BranchID);
            preparedStatement.setInt(3, Stock);
            preparedStatement.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EmployeeShow() {
        try {
            
            preparedStatement = conn.prepareStatement("select * from Employee");
            rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                System.out.println("There is no data");
            } else {
                do {
                    String name = rs.getString("NAME");
                    System.out.println("Name: " + name);
                }
                while (rs.next());
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void ProductsShow() {
        try {
            
            preparedStatement = conn.prepareStatement("select * from Products");
            rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                System.out.println("There is no data");
            } else {
                do {
                    String name = rs.getString("NAME");
                    System.out.println("Name: " + name);
                }
                while (rs.next());
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List<Products> getProducts() {
        List<Products> listProducts = new ArrayList();

        try{
            String query = "SELECT * FROM Products inner join ProductType on Products.ProductTypeID = ProductType.ID inner join ProductStock on Products.ID = ProductStock.ProductID inner join Branch on ProductStock.BranchID = Branch.ID";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                Products p = new Products(rs.getInt("ID"), rs.getString("Name"), rs.getInt("Price"), rs.getString("Description"), rs.getString("ProductType.Name"), rs.getInt("ProductStock.Stock"), rs.getString("Branch.Name"));
                listProducts.add(p);
            }
            

            return listProducts;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProducts;
        }
    }
    public List<String> getProductName() {
        List<String> listProductName = new ArrayList();

        try{
            String query = "SELECT * FROM Products";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listProductName.add(rs.getString("Name"));
            }

            
            return listProductName;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProductName;
        }
    }
    
     public List<Integer> getProductIds() {
        List<Integer> listProductName = new ArrayList();

        try{
            String query = "SELECT * FROM Products";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listProductName.add(rs.getInt("ID"));
            }

            
            return listProductName;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProductName;
        }
    }
    
    
    public List<String> getEmployeeName() {
        List<String> listEmployeeName = new ArrayList();

        try{
            String query = "SELECT * FROM Employee";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listEmployeeName.add(rs.getString("NAME"));
            }

            
            return listEmployeeName;
        }
        catch(Exception e){
            e.printStackTrace();
            return listEmployeeName;
        }
    }
    public List<String> getProductPrice() {
        List<String> listProductPrice = new ArrayList();

        try{
            String query = "SELECT * FROM Products";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listProductPrice.add(Integer.toString(rs.getInt("Price")));
            }

            
            return listProductPrice;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProductPrice;
        }
    }
    public List<String> getTransactionTotal() {
        List<String> listProductPrice = new ArrayList();

        try{
            String query = "SELECT * FROM Transaction";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listProductPrice.add(Integer.toString(rs.getInt("Total")));
            }

            
            return listProductPrice;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProductPrice;
        }
    }
    
    public List<String> getProductType() {
        List<String> listProductType = new ArrayList();

        try{
            String query = "SELECT * FROM ProductType";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listProductType.add(rs.getString("Name"));
            }

            
            return listProductType;
        }
        catch(Exception e){
            e.printStackTrace();
            return listProductType;
        }
    }
    public List<String> getBranch() {
        List<String> listBranch = new ArrayList();

        try{
            String query = "SELECT * FROM Branch";
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();


            while(rs.next()){
                listBranch.add(rs.getString("Name"));
            }
            

            return listBranch;
        }
        catch(Exception e){
            e.printStackTrace();
            return listBranch;
        }
    }
    public void BranchShow() {
        try {
            preparedStatement = conn.prepareStatement("select * from Branch");
            rs = preparedStatement.executeQuery();
            System.out.println("Records from database");
            if (!rs.next()) {
                System.out.println("There is no data");
            } else {
                do {
                    String name = rs.getString("Name");
                    String area = rs.getString("Area");
                    String address = rs.getString("Address");
                    String building = rs.getString("Building");
                    String telephone = rs.getString("Telephone");
                    System.out.println("Name: " + name);
                    System.out.println(area);
                    System.out.println(address);
                    System.out.println(building);
                    System.out.println(telephone);
                }
                while (rs.next());
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void view() {
        try {
            preparedStatement = conn.prepareStatement("select * from Transaction inner join Employee on Transaction.EmployeeID = Employee.ID inner join PaymentType on Transaction.PaymentTypeID = PaymentType.ID inner join Branch on Transaction.BranchID = Branch.ID inner join Tax on Transaction.TaxID = Tax.ID inner join TransactionDetail on TransactionDetail.TransactionID = Transaction.ID inner join Products on TransactionDetail.ProductID = Products.ID");
            rs = preparedStatement.executeQuery();
            System.out.println("Records from database");
            if (!rs.next()) {
                System.out.println("There is no data");
            } else {
                do {
                    String name = rs.getString("Employee.NAME");
                    String paymentname = rs.getString("PaymentType.Name");
                    String branchname = rs.getString("Branch.Name");
                    int tax = rs.getInt("Tax.Value");
                    String productname = rs.getString("Products.Name");
                    System.out.println(name);
                    System.out.println(paymentname);
                    System.out.println(branchname);
                    System.out.println(tax);
                    System.out.println(productname);

                }
                while (rs.next());
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void UpdateEmployeename(int id, Employee e) {
        try {
            PreparedStatement preparedStatement1;
            String query = "UPDATE Employee SET Name = ? , Gender = ? , PhoneNumber = ? , BranchID = ? WHERE ID = ?";
            preparedStatement1 = conn.prepareStatement(query);
            preparedStatement1.setString(1, e.Name);
            preparedStatement1.setString(2, e.Gender);
            preparedStatement1.setString(3, e.PhoneNumber);
            preparedStatement1.setInt(4, e.BranchID);
            preparedStatement1.setInt(5, id);

            preparedStatement1.executeUpdate();
            
        } catch (Exception ex) {
            System.err.println("Got an exception");
            System.err.println(ex.getMessage());
        }
    }
    public void deleteProducts(int id) {
        String query = "DELETE FROM Products WHERE ID = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


//    public void updateSubtract(int id, int balance) {
//        try {
//            PreparedStatement preparedStatement1;
//            String query = "UPDATE user SET balance = ? WHERE bank_id = ?";
//            preparedStatement1 = conn.prepareStatement(query);
//            preparedStatement1.setInt(1, subtractBalance(id, balance));
//            preparedStatement1.setInt(2, id);
//            preparedStatement1.executeUpdate();
//        } catch (Exception e) {
//            System.err.println("Got an exception");
//            System.err.println(e.getMessage());
//        }
//    }
//
//


}



