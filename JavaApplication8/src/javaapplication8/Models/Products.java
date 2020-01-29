/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8.Models;

/**
 *
 * @author user
 */
public class Products {
    public int ID;
    public String Name;
    public int Price;
    public String Description;
    public String ProductTypeID;
    public int ProductStock;
    public String BranchName;
    public Products(int id, String name, int price, String description, String productTypeID, int productStock, String branchName){
        ID = id;
        Name = name;
        Price = price;
        Description = description;
        ProductTypeID = productTypeID;
        ProductStock = productStock;
        BranchName = branchName;

    }
}

