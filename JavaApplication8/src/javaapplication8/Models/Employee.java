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

import java.util.Date;

public class Employee {
    public Integer ID;
    public String Name;
    public String Gender;
    public String PhoneNumber;
    public String JoinDate;
    public Integer BranchID;

    public Employee(String name, String gender, String phoneNumber, String joinDate, Integer branchID) {
//        this.ID = ID;
        Name = name;
        Gender = gender;
        PhoneNumber = phoneNumber;
        JoinDate = joinDate;
        BranchID = branchID;
    }
}

