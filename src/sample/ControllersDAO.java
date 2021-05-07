package sample;
import Connector.ConnectionToMYSQLDB;
import java.util.ArrayList;
/*
    Data access object serves as an intermediate between the controller and the connection class
*/
public class ControllersDAO implements Cloneable{
    //Retrieves recipe titles based on the ingredient list specified by user
    protected ArrayList<String> getRecipeTitles(ArrayList<String> ingredientList) throws Exception {
        ArrayList<String> recipeString = ConnectionToMYSQLDB.getRecipeFromIngredients(ingredientList);
        return recipeString;
    }
    protected ArrayList<String> getRecipeInfo(String str) throws Exception {
        //call the database here and retrieve the ingredients, picture and directions
        ArrayList<String> finalList = ConnectionToMYSQLDB.getIngredientsFromRecipe(str);
        return finalList;
    }
    //Search pantry using username and return the array list of recipe strings
    protected ArrayList<String> getRecipeFromUser(String user) throws Exception {
        return ConnectionToMYSQLDB.getAllRecipesForPantry(user);
    }
    //enter record parameters accepts the user name and password
    //if the boolean response from the check user method is false, this means
    //the username is taken, so the user will be prompted with an error message
    //otherwise a 7 digit random key will be user (if ever needed for user reference in the future)
    //very low probability the key will be selected twice
    protected boolean enterRecord(String user, String pass) throws Exception {
        if(!ConnectionToMYSQLDB.checkUser(user)){
            return false;
        }else{
            //incase wanting to use in the future, this will generate a random key for each new registered user, stored in the user table.
            int max = 999999;
            int min = 1;
            int random_int = (int)(Math.random() * (max - min + 1) + min);
            //register the data for the user specifying the data for each column in the user info table in the database
            ConnectionToMYSQLDB.registerUser(user, pass, random_int);
            return true;
        }
    }
    //Pass in the recipe name to be compared against each recipe name entry in the recipe data table
    //Since the recipe directions are stored in chronological order in the table
    //The query results will appear to be in order
    //This works because the recipe directions will never be added or altered again
    //The last entry being passed back to the user will contain the hyperlink string, specifically accessed by calling the last
    //element in the list
    protected ArrayList<String> getRecipeDirections(String recipeName) throws Exception {
        return ConnectionToMYSQLDB.getDirections(recipeName);
    }
    //If the recipe can't be added to the pantry (the pantry already exists in the users pantry), return false
    //Otherwise return true, which will display a success message
    protected boolean addToUserPantry(String user, String recipe) throws Exception {
        if(!ConnectionToMYSQLDB.addToPantryTable(user, recipe)){
            return false;
        }else{
            return true;
        }
    }
    //Check if the username and and encrypted password are correct
    //If so return true and let the user login in
    //Otherwise return false (Either username or password was incorrect
    protected boolean retrieveRecord(String user, String pass) throws Exception {
        if(ConnectionToMYSQLDB.checkUserAndPass(user,pass)){
            return true;
        }
        return false;
    }
    //Deletes the users pantry item
    //Accepts the username and password, if the recipe doesn't exist when being compared against username return false
    //Otherwise the recipe was found so return true that the entry was successfully deleted
    protected boolean deleteFromUserPantry(String user, String recipe) throws Exception {
        if (!ConnectionToMYSQLDB.checkIfEntryExists(user, recipe)) {
            return false;
        } else {
            ConnectionToMYSQLDB.deleteFromPantry(user, recipe);
            return true;
        }
    }
    protected boolean checkAdmin(String user) throws Exception {
        return ConnectionToMYSQLDB.checkAdministrator(user);
    }
}
