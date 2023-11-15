/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author custo
 */
public class connectDAO {
    private static String Url;
    private static String User;
    private static String Password;

    public  String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public  String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
       
}
