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
public class ReviewBean implements Serializable{
    
/**
 * -- Returns all reviews in database --
 * @return List of Review objects
 */
    public List<Review> getReviews() {
        try {
            String sql = "SELECT recipeId as id, Recipes.name, reviewValue, Reviews.description, Reviews.userId, Users.username FROM Reviews "
                    + "INNER JOIN Users ON Reviews.userId = Users.id "
                    + "INNER JOIN Recipes ON Reviews.recipeId = Recipes.id "
                    + "ORDER BY Recipes.id";
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            List<Review> reviews = new ArrayList<>();
            while(data.next()) {
                Review review = new Review();
                review.setRecipeId(data.getInt("id"));
                review.setRecipeName(data.getString("name"));
                review.setReview(data.getString("description"));
                review.setUserId(data.getInt("userId"));
                review.setReviewValue(data.getInt("reviewValue"));
                review.setUsername(data.getString("username"));
                reviews.add(review);
            }
            connection.close();
            return reviews;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

/**
 * -- Delete review --
 * @param recipe recipeid on the review that will be deleted
 * @param user userid on the review that will be deleted
 */
    public void delete(int recipe, int user) {
        try {
            String sql = "DELETE FROM Reviews WHERE recipeId = ? AND userId = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recipe);
            stmt.setInt(2, user);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
}
