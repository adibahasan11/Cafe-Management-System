package sample.Admin;

import javafx.scene.control.CheckBox;

public class Employee {

    public String E_ID;
    public String E_Name;
    public String E_Mail;
    public String E_Address;
    public int E_M_ID;
    public String E_salary;
    public String E_Join_Date;


    Employee(String id, String name, String email, String address, int admin_id, String j_date, String Salary){
        this.E_ID = id;
        this.E_Name = name;
        this.E_Mail = email;
        this.E_Address = address;
        this.E_M_ID = admin_id;
        this.E_Join_Date=j_date;
        this.E_salary = Salary;
    }

    public String getE_Address() {
        return E_Address;
    }

    public void setE_Address(String e_Address) {
        E_Address = e_Address;
    }

    public String getE_Designation() {
        return E_salary;
    }

    public void setE_Designation(String e_Designation) {
        E_salary = e_Designation;
    }

    public String getE_ID() {
        return E_ID;
    }

    public void setE_ID(String e_ID) {
        E_ID = e_ID;
    }

    public String getE_Join_Date() {
        return E_Join_Date;
    }

    public void setE_Join_Date(String e_Join_Date) {
        E_Join_Date = e_Join_Date;
    }

    public int getE_M_ID() {
        return E_M_ID;
    }

    public void setE_M_ID(int e_M_ID) {
        E_M_ID = e_M_ID;
    }

    public String getE_Mail() {
        return E_Mail;
    }

    public void setE_Mail(String e_Mail) {
        E_Mail = e_Mail;
    }

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        E_Name = e_Name;
    }
    public String getE_salary() {
        return E_salary;
    }

    public void setE_salary(String e_salary) {
        E_salary = e_salary;
    }

}
