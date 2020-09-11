package puzzle.otherClassesAndStyles;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private int highRank;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighRank() {
        return highRank;
    }

    public void setHighRank(int highRank) {
        this.highRank = highRank;
    }
}
