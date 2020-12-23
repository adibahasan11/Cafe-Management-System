package tableviewemployess;

public class employess {
    int ID,Salary;
    String Name,Email,Address,Designation,Date;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public int getSalary() {
        return Salary;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getDate() {
        return Date;
    }

    public employess(int ID, int salary, String name, String email, String address, String designation, String date) {
        this.ID = ID;
        Salary = salary;
        Name = name;
        Email = email;
        Address = address;
        Designation = designation;
        Date = date;
    }
}
