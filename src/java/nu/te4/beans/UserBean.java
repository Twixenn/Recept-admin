/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.utilities.ConnectionFactory;

/**
 *
 * @author Fia
 */
@Named
@SessionScoped
public class UserBean implements Serializable{
    private int editable;
    private String editUser, editImage;

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public String getEditImage() {
        return editImage;
    }

    public void setEditImage(String editImage) {
        this.editImage = editImage;
    }
/**
 * -- Return all users in database --
 * @return List of User objects
 */   
    public List<User> getUsers() {
        try {
            String sql = "SELECT * FROM Users";
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(data.next()) {
                User user = new User();
                user.setId(data.getInt("id"));
                user.setUsername(data.getString("username"));
                user.setUserId(data.getString("userId"));
                user.setImage(data.getString("image"));
                if(user.getId() == this.editable) {
                    user.setEditable(true);
                }
                users.add(user);
            }
            connection.close();
            return users;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

/**
 * -- Edit user --
 * Changes value on username and image on a user
 * @param user userobject with the userid you want to edit
 */
    public void edit(User user) {
        this.editable = 0;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE Users SET username = ?, image = ? WHERE id = ?");
            stmt.setString(1, this.editUser);
            stmt.setString(2, this.editImage);
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

/**
 * -- Change editmode to true --
 * Sets private values equal to userparameter value
 * @param user userobject with id, username and image
 */    
    public void editMode(User user) {
        user.setEditable(true);
        this.editable = user.getId();
        this.editUser = user.getUsername();
        this.editImage = user.getImage();
    }

/**
 * -- Delete user --
 * @param id userid on the user that will be deleted
 */
    public void delete(int id) {
        try {
            String sql = "DELETE FROM Users WHERE id = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
}
