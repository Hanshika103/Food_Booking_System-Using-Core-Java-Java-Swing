package model; // Model package (stores only data classes)

// This class represents login credentials of a user
public class login {

    // ==============================
    // FIELDS (DATA MEMBERS)
    // ==============================

    private String email; // stores user email
    private String password; // stores user password

    // ==============================
    // DEFAULT CONSTRUCTOR
    // ==============================
    public login() {} 
    // empty constructor (used when object is created without values)

    // ==============================
    // PARAMETERIZED CONSTRUCTOR
    // ==============================
    public login(String email, String password) {

        this.email = email; // assign email
        this.password = password; // assign password
    }

    // ==============================
    // GETTERS (READ DATA)
    // ==============================

    public String getEmail() {
        return email; // return email
    }

    public String getPassword() {
        return password; // return password
    }

    // ==============================
    // SETTERS (UPDATE DATA)
    // ==============================

    public void setEmail(String email) {
        this.email = email; // update email
    }

    public void setPassword(String password) {
        this.password = password; // update password
    }
}
