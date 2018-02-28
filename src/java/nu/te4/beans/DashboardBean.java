/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import java.io.Serializable;
import java.sql.Connection;
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
public class DashboardBean implements Serializable{
    public Dashboard getDashboard() {
        try {
            String sql = "SELECT COUNT(Recipes.id) as recipes, (SELECT COUNT(Users.id) FROM Users) as users, (SELECT COUNT(*) FROM Reviews) as reviews FROM Recipes";
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            Dashboard dashboard = new Dashboard();
            while(data.next()) {
                dashboard.setRecipies(data.getInt("recipes"));
                dashboard.setUsers(data.getInt("users"));
                dashboard.setReviews(data.getInt("reviews"));
            }
            connection.close();
            return dashboard;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }
}
