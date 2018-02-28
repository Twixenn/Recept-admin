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
public class RecipeBean implements Serializable {
    private String searchText;
    private int editable;

    public String getSearchText() {
        if(searchText == null) {
            searchText = "";
        }
        return searchText;
    }

    public void setSearchText(String searchText) {
        if(searchText == null) {
            searchText = "";
        }
        this.searchText = searchText;
    }

/**
 * -- Returns all recipies in database --
 * @return List of Recipe objects
 */
    public List<Recipe> getRecipies() {
        try {
            String sql = "SELECT Recipes.id, name, description, Recipes.userId, Users.username FROM Recipes INNER JOIN Users ON Recipes.userId = Users.id ORDER BY Recipes.id";
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            List<Recipe> recipes = new ArrayList<>();
            while(data.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(data.getInt("id"));
                recipe.setName(data.getString("name"));
                recipe.setUserId(data.getInt("userId"));
                recipe.setUsername(data.getString("username"));
                recipes.add(recipe);
            }
            connection.close();
            return recipes;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }
    
/**
 * -- Returns recipies with a name that contains serchtext --
 * @return List of Recipe objects
 */
    public List<Recipe> search() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT Recipes.id, name, description, Recipes.userId, Users.username FROM Recipes INNER JOIN Users ON Recipes.userId = Users.id WHERE name LIKE ?");
            stmt.setString(1, "%" + this.searchText + "%");
            ResultSet data = stmt.executeQuery();
            List<Recipe> recipies = new ArrayList<>();
            while(data.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(data.getInt("id"));
                recipe.setName(data.getString("name"));
                recipe.setUserId(data.getInt("userId"));
                recipe.setUsername(data.getString("username"));
                recipies.add(recipe);
            }
            connection.close();
            return recipies;
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return null;
    }
    
/**
 * -- Delete recipe --
 * @param id the recipeid that will be deleted
 */ 
    public void delete(int id) {
        try {
            String sql = "DELETE FROM Recipes WHERE id = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            System.out.println(stmt);
            stmt.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
