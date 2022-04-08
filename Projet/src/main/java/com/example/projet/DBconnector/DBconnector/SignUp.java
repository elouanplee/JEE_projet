package com.example.projet.DBconnector;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;


/**
 * Classe permettant de s'inscrire sur le site
 */
@Named
@RequestScoped
public class SignUp implements Serializable {
    private String login1;
    private String password1;
    private String firstname;
    private String lastname;
    private String confirmPassword;

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getlastName() {
        return lastname;
    }

    public void setlastName(String lastname) {
        this.lastname = lastname;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }

    /**
     * Méthode pour vérifier la validité des données et les insérer dans la BDD
     *
     * @return redirection vers les pages xhtml
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public String dispatch() throws SQLException, NoSuchAlgorithmException {
        if (confirmPassword.equals(password1)) {
            PreparedStatement query = DBConnection.getInstance().prepareStatement("INSERT INTO users (`psd`, `name`, `pwd`) VALUES (?,?,?);");
            query.setString(1, login1);
            query.setString(2, firstname);
            query.setString(2, lastname);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            query.setString(3, Base64.getEncoder().encodeToString(digest.digest(password1.getBytes(StandardCharsets.UTF_8))));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login1);
            query.execute();
            return query.getUpdateCount() > 0 ? "home.xhtml" : "index.xhtml";
        }
        return "index.xhtml";

    }

}
