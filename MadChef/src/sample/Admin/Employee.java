package sample.Admin;

public class Employee {

    public String E_ID;
    public String E_Name;
    public String E_Mail;
    public String E_PW;
    public String E_Address;
    public int E_M_ID;
    public String E_Designation;
    public String E_Join_Date;

    Employee(String id, String name, String email, String address, int manager_id, String j_date, String designation){
        this.E_ID = id;
        this.E_Name = name;
        this.E_Mail = email;
        this.E_Address = address;
        this.E_M_ID = manager_id;
        this.E_Designation = designation;
        this.E_Join_Date = j_date;
    }
}
