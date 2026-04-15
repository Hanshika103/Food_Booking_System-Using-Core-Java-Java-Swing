package model; // Model layer package (stores data classes only)

// This class represents a user registration record
public class Registration {

    // ==============================
    // FIELDS (DATA MEMBERS)
    // ==============================

    private int regId; // unique ID from database (auto-increment)
    private String name; // user name
    private String email; // user email
    private String password; // user password

    // ==============================
    // CONSTRUCTOR 1 (FOR INSERTING NEW USER)
    // ==============================
    public Registration(String name, String email, String password) {

        this.name = name; // assign name
        this.email = email; // assign email
        this.password = password; // assign password
    }

    // ==============================
    // CONSTRUCTOR 2 (FOR FETCHING FROM DATABASE)
    // ==============================
    public Registration(int regId, String name, String email, String password) {

        this.regId = regId; // assign ID from DB
        this.name = name; // assign name
        this.email = email; // assign email
        this.password = password; // assign password
    }

    // ==============================
    // GETTERS (READ DATA)
    // ==============================

    public int getRegId() {
        return regId; // return user ID
    }

    public String getName() {
        return name; // return name
    }

    public String getEmail() {
        return email; // return email
    }

    public String getPassword() {
        return password; // return password
    }

    // ==============================
    // SETTERS (UPDATE DATA)
    // ==============================

    public void setRegId(int regId) {
        this.regId = regId; // update ID
    }

    public void setName(String name) {
        this.name = name; // update name
    }

    public void setEmail(String email) {
        this.email = email; // update email
    }

    public void setPassword(String password) {
        this.password = password; // update password
    }
}
