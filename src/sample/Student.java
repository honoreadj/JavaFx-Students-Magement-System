package sample;

public class Student {
    private String lastName, firstName, department, email, address;

    public Student(String lastName, String firstName, String department, String email, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.email = email;
        this.address = address;
    }

    public Student(String part, String s) {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
